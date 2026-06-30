# Hướng Dẫn Thêm Từ Vựng JLPT

## 1. Cấu Trúc Dữ Liệu

### Bảng Level_Words:

```
id       | kanji      | hiragana   | meaning         | level
---------|------------|------------|-----------------|------
1        | 私         | わたし     | Tôi (chính thức)| N5
2        | 彼         | かれ       | Anh ấy          | N5
3        | 女         | おんな     | Phụ nữ          | N5
...
```

## 2. Cách Thêm Từ Vựng

### Phương Pháp 1: Sửa trực tiếp trong code (Dễ nhất)

Mở file: `app/src/main/java/com/example/mogigoi/MyDbHelper.kt`

Tìm hàm `insertSampleData()` và thêm từ vào danh sách:

```kotlin
private fun insertSampleData(db: SQLiteDatabase?) {
    val sampleWords = listOf(
        // N5 Level
        Triple("私", "わたし", "Tôi (chính thức)"),
        Triple("彼", "かれ", "Anh ấy"),
        Triple("女", "おんな", "Phụ nữ"),
        Triple("男", "おとこ", "Nam giới"),

        // Thêm từ mới tại đây
        Triple("新しい", "あたらしい", "Mới"),
        Triple("古い", "ふるい", "Cũ"),

        // N4 Level
        Triple("経験", "けいけん", "Kinh nghiệm"),
        // ... và các từ khác
    )

    db?.beginTransaction()
    try {
        for ((i, word) in sampleWords.withIndex()) {
            val level = if (i < 10) "N5" else "N4"  // Điều chỉnh số từ N5/N4
            // ...
        }
    }
}
```

### Phương Pháp 2: Thêm từng từ (Chi tiết hơn)

```kotlin
// Thêm từ riêng lẻ với level cụ thể
Triple("新しい", "あたらしい", "Mới"),  // Sẽ được gán level N5 nếu i < 10

// Hoặc thêm một cách rõ ràng:
val words = mapOf(
    "N5" to listOf(
        Triple("私", "わたし", "Tôi"),
        Triple("彼", "かれ", "Anh ấy"),
    ),
    "N4" to listOf(
        Triple("経験", "けいけん", "Kinh nghiệm"),
        Triple("技術", "ぎじゅつ", "Kỹ thuật"),
    )
)
```

## 3. Ví Dụ Thêm Từ Vựng N5

```kotlin
private fun insertSampleData(db: SQLiteDatabase?) {
    val sampleWords = listOf(
        // N5 Level - Cơ bản
        Triple("私", "わたし", "Tôi (chính thức)"),
        Triple("彼", "かれ", "Anh ấy"),
        Triple("女", "おんな", "Phụ nữ"),
        Triple("男", "おとこ", "Nam giới"),
        Triple("子", "こ", "Trẻ em"),
        Triple("学生", "がくせい", "Học sinh"),
        Triple("先生", "せんせい", "Giáo viên"),
        Triple("家", "いえ", "Nhà"),
        Triple("学校", "がっこう", "Trường học"),
        Triple("会社", "かいしゃ", "Công ty"),
        // Thêm 10 từ mới
        Triple("新しい", "あたらしい", "Mới"),
        Triple("古い", "ふるい", "Cũ"),
        // ... tiếp tục

        // N4 Level
        Triple("経験", "けいけん", "Kinh nghiệm"),
        // ... các từ N4
    )

    // Phần còn lại giữ nguyên
}
```

## 4. Danh Sách Từ Vựng Mẫu

### N5 (10 từ):

| Kanji | Hiragana | Meaning          |
| ----- | -------- | ---------------- |
| 私    | わたし   | Tôi (chính thức) |
| 彼    | かれ     | Anh ấy           |
| 女    | おんな   | Phụ nữ           |
| 男    | おとこ   | Nam giới         |
| 子    | こ       | Trẻ em           |
| 学生  | がくせい | Học sinh         |
| 先生  | せんせい | Giáo viên        |
| 家    | いえ     | Nhà              |
| 学校  | がっこう | Trường học       |
| 会社  | かいしゃ | Công ty          |

### N4 (10 từ):

| Kanji  | Hiragana | Meaning     |
| ------ | -------- | ----------- |
| 経験   | けいけん | Kinh nghiệm |
| 技術   | ぎじゅつ | Kỹ thuật    |
| 問題   | もんだい | Vấn đề      |
| 方法   | ほうほう | Phương pháp |
| 言葉   | ことば   | Từ ngữ      |
| 気持ち | きもち   | Cảm xúc     |
| 意見   | いけん   | Ý kiến      |
| 理由   | りゆう   | Lý do       |
| 結果   | けっか   | Kết quả     |
| 成功   | せいこう | Thành công  |

## 5. Lưu Ý Quan Trọng

### Format Dữ Liệu:

- **Kanji**: Chữ Hán (ví dụ: 私, 彼)
- **Hiragana**: Phiên âm dạng Hiragana (ví dụ: わたし, かれ)
- **Meaning**: Ý nghĩa bằng Tiếng Việt
- **Level**: N5, N4, N3, N2, hoặc N1

### Quy Tắc Thêm Từ:

1. Thêm từ mới vào danh sách `sampleWords`
2. Đảm bảo format: `Triple("kanji", "hiragana", "meaning")`
3. Rebuild project: `./gradlew clean build`
4. Chạy ứng dụng để kiểm tra

### Số Từ Trong Mỗi Level (Mặc định):

- N5: Từ 0-9 (10 từ)
- N4: Từ 10-19 (10 từ)
- N3, N2, N1: Không có dữ liệu mặc định (thêm cần thiết)

Để thay đổi, sửa phần này:

```kotlin
val level = if (i < 10) "N5" else "N4"
```

## 6. Cách Xóa Dữ Liệu Cũ

Khi thêm dữ liệu mới, cơ sở dữ liệu cũ sẽ bị xóa tự động và tạo lại từ scratch.

Hoặc bạn có thể xóa app data:

- Settings → Apps → JLPT Vocabulary → Storage → Clear Data

## 7. Kiểm Tra Dữ Liệu

Sau khi build & run:

1. Chọn cấp độ từ Home
2. Chọn FlashCard hoặc Quiz
3. Kiểm tra xem từ vựng có hiển thị không

## 8. Thêm Từ Vựng Từ File (Nâng Cao)

Nếu muốn thêm nhiều từ từ file CSV hoặc JSON, bạn có thể:

1. Tạo file `vocabulary.json` trong `assets/`
2. Đọc file và insert vào database
3. (Cần code thêm - hiện không hỗ trợ sẵn)

## 9. Troubleshooting

### Vấn đề: Từ mới không hiển thị

- **Giải pháp**:
  1. Rebuild project (`./gradlew clean build`)
  2. Xóa app data từ phone/emulator
  3. Chạy lại ứng dụng

### Vấn đề: Lỗi compile

- **Giải pháp**:
  1. Kiểm tra format Triple: `Triple("kanji", "hiragana", "meaning")`
  2. Kiểm tra dấu ngoặc đóng: `)`
  3. Kiểm tra dấu phẩy: `,` giữa các từ

## 10. Ví Dụ Hoàn Chỉnh

```kotlin
private fun insertSampleData(db: SQLiteDatabase?) {
    val sampleWords = listOf(
        // N5 Level - 10 từ
        Triple("私", "わたし", "Tôi (chính thức)"),
        Triple("彼", "かれ", "Anh ấy"),
        Triple("女", "おんな", "Phụ nữ"),
        Triple("男", "おとこ", "Nam giới"),
        Triple("子", "こ", "Trẻ em"),
        Triple("学生", "がくせい", "Học sinh"),
        Triple("先生", "せんせい", "Giáo viên"),
        Triple("家", "いえ", "Nhà"),
        Triple("学校", "がっこう", "Trường học"),
        Triple("会社", "かいしゃ", "Công ty"),

        // N4 Level - 10 từ
        Triple("経験", "けいけん", "Kinh nghiệm"),
        Triple("技術", "ぎじゅつ", "Kỹ thuật"),
        Triple("問題", "もんだい", "Vấn đề"),
        Triple("方法", "ほうほう", "Phương pháp"),
        Triple("言葉", "ことば", "Từ ngữ"),
        Triple("気持ち", "きもち", "Cảm xúc"),
        Triple("意見", "いけん", "Ý kiến"),
        Triple("理由", "りゆう", "Lý do"),
        Triple("結果", "けっか", "Kết quả"),
        Triple("成功", "せいこう", "Thành công"),
    )

    db?.beginTransaction()
    try {
        for ((i, word) in sampleWords.withIndex()) {
            val level = if (i < 10) "N5" else "N4"
            val insertQuery = """
                INSERT INTO $TABLE_LEVEL_WORDS ($COL_KANJI, $COL_HIRAGANA, $COL_MEANING, $COL_LEVEL)
                VALUES ('${word.first}', '${word.second}', '${word.third}', '$level')
            """.trimIndent()
            db?.execSQL(insertQuery)
        }
        db?.setTransactionSuccessful()
    } finally {
        db?.endTransaction()
    }
}
```

Chúc bạn học tập hiệu quả! 🎌📚
