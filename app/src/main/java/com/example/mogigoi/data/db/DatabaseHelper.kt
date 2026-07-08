package com.example.mogigoi.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream

/**
 * SQLiteOpenHelper that ships a pre-built database from assets/database/vocabulary.db.
 *
 * On first run (or whenever the DB file is absent) the asset is copied to the
 * app's internal database directory so it can be opened normally.
 */
class DatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME    = "vocabulary.db"
        const val DB_VERSION = 1
        const val ASSET_PATH = "database/vocabulary.db"

        // Table names
        const val TABLE_LEVELS     = "levels"
        const val TABLE_LESSONS    = "lessons"
        const val TABLE_VOCABULARY = "vocabulary"
    }

    /** Path where Android stores the app's SQLite databases. */
    private val dbPath: String
        get() = context.getDatabasePath(DB_NAME).absolutePath

    /**
     * Copy the bundled asset DB to internal storage if it doesn't exist yet,
     * then open and return it in read-write mode.
     */
    fun openDatabase(): SQLiteDatabase {
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            // Ensure parent directory exists
            dbFile.parentFile?.mkdirs()
            copyFromAssets(dbFile)
        }
        return SQLiteDatabase.openDatabase(
            dbPath,
            null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }

    private fun copyFromAssets(destination: File) {
        context.assets.open(ASSET_PATH).use { input ->
            FileOutputStream(destination).use { output ->
                val buffer = ByteArray(4096)
                var bytesRead: Int
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                }
                output.flush()
            }
        }
    }

    // ── SQLiteOpenHelper callbacks (unused – DB is managed manually) ──────────

    override fun onCreate(db: SQLiteDatabase) { /* no-op: DB is pre-built */ }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // On upgrade: delete the old copy so openDatabase() copies fresh asset.
        val dbFile = File(dbPath)
        if (dbFile.exists()) dbFile.delete()
    }
}
