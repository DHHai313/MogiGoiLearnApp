# JLPT Vocabulary Learning App

Ứng dụng học từ vựng JLPT cho Android với 2 chế độ học: FlashCard và Quiz.

## 🎯 Tính Năng Chính

### 1. **FlashCard Mode (Chế độ Thẻ Ghi Nhớ)**

- Học từng từ một cách từ từ
- Nhấn vào thẻ để lật và xem ý nghĩa
- Điều hướng bằng nút Previous/Next
- Nút Shuffle để trộn các từ ngẫu nhiên
- Hiển thị tiến độ (ví dụ: 1/10)

**Cách sử dụng:**

1. Chọn cấp độ JLPT (N5, N4, N3, N2, N1) từ Home
2. Chọn "FlashCard" từ màn hình chế độ học
3. Nhấp vào thẻ để lật và xem ý nghĩa
4. Dùng nút Previous/Next để điều hướng
5. Nhấn Shuffle để trộn lại các từ

### 2. **Quiz Mode (Chế độ Trắc Nghiệm)**

- 4 đáp án cho mỗi câu hỏi
- Phản hồi tức thời (Đúng/Sai)
- Hiển thị câu trả lời đúng nếu trả lời sai
- Tính điểm tự động
- Kết thúc với tổng kết quả

**Cách sử dụng:**

1. Chọn cấp độ JLPT từ Home
2. Chọn "Quiz" từ màn hình chế độ học
3. Đọc câu hỏi và chọn ý nghĩa đúng
4. Nhấn "Câu tiếp theo →" sau khi trả lời
5. Xem tổng kết quả ở cuối quiz

## 🏗️ Cấu Trúc Dự Án

```
MogiGoi/
├── app/
│   └── src/main/
│       ├── java/com/example/mogigoi/
│       │   ├── MainActivity.kt              # Màn hình chọn cấp độ
│       │   ├── MyDbHelper.kt                # Quản lý SQLite Database
│       │   ├── data/
│       │   │   ├── model/
│       │   │   │   ├── Level.kt             # Model cấp độ JLPT
│       │   │   │   ├── Word.kt              # Model từ vựng
│       │   │   │   └── Lesson.kt            # Model bài học
│       │   │   └── repository/
│       │   │       └── WordRepository.kt    # Repository quản lý dữ liệu
│       │   └── ui/
│       │       ├── home/
│       │       │   └── LevelAdapter.kt      # Adapter hiển thị cấp độ
│       │       ├── flashcard/
│       │       │   └── FlashCardActivity.kt # Chế độ FlashCard
│       │       ├── quiz/
│       │       │   └── QuizActivity.kt      # Chế độ Quiz
│       │       └── mode/
│       │           └── LearningModeActivity.kt # Chọn chế độ học
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml
│           │   ├── activity_flashcard.xml
│           │   ├── activity_quiz.xml
│           │   └── activity_learning_mode.xml
│           ├── values/
│           │   ├── strings.xml
│           │   └── colors.xml
│           └── anim/
│               └── flip_animation.xml       # Animation lật thẻ
```

## 💾 Cơ Sở Dữ Liệu

### Bảng: Level_Words

```sql
CREATE TABLE IF NOT EXISTS Level_Words (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    kanji TEXT NOT NULL,
    hiragana TEXT NOT NULL,
    meaning TEXT NOT NULL,
    level TEXT NOT NULL
)
```

### Cấp Độ JLPT:

- **N5**: Cơ bản nhất
- **N4**: Sơ cấp
- **N3**: Trung cấp
- **N2**: Cao
- **N1**: Cao nhất

## 🎨 Giao Diện

### Màu Sắc:

- **Primary Color**: #2196F3 (Xanh dương)
- **Accent Color**: #FF6B6B (Đỏ)
- **Card Front**: #3498DB (Thẻ mặt trước)
- **Card Back**: #E74C3C (Thẻ mặt sau)
- **Correct**: #27AE60 (Xanh lá - Đúng)
- **Wrong**: #E74C3C (Đỏ - Sai)

### Thành Phần UI:

- Toolbar tiêu đề ở trên cùng
- CardView cho hiển thị từ vựng
- Button điều hướng
- RecyclerView cho danh sách

## 🔧 Cài Đặt & Chạy

### Yêu Cầu:

- Android SDK 24 (Android 7.0) trở lên
- Target SDK 36
- Kotlin

### Build & Run:

```bash
cd MogiGoi
./gradlew build         # Build APK
./gradlew assembleDebug # Build debug APK
```

## 📝 Hướng Dẫn Thêm Từ Vựng

### Thêm từ vựng vào cơ sở dữ liệu:

Mở `MyDbHelper.kt` và thêm vào hàm `insertSampleData()`:

```kotlin
Triple("漢字", "かんじ", "Chữ Hán"),
Triple("ひらがな", "ひらがな", "Chữ Hiragana"),
```

Format: `Triple(kanji, hiragana, meaning)` hoặc chỉ định `level` ("N5", "N4", v.v.)

## 🎓 Cách Sử Dụng Ứng Dụng

### Từ Home:

1. Xem 5 cấp độ JLPT (N5-N1)
2. Nhấn vào cấp độ muốn học
3. Chọn chế độ: FlashCard hoặc Quiz
4. Bắt đầu học!

### FlashCard:

- Nhấp thẻ để lật
- ← Trước / Sau → để điều hướng
- 🔀 Trộn để xáo trộn từ
- Tiến độ hiển thị ở trên cùng

### Quiz:

- Chọn 1 trong 4 đáp án
- Xem kết quả ngay lập tức
- → Câu tiếp theo để tiếp tục
- Kết quả cuối cùng ở cuối quiz

## 🚀 Tính Năng Tương Lai

- [ ] Thống kê và báo cáo tiến độ
- [ ] Lưu từ yêu thích
- [ ] Mode học theo phần (Lesson)
- [ ] Phát âm từ vựng (TTS)
- [ ] Ôn tập từ khó
- [ ] Bảng xếp hạng

## 📄 Giấy Phép

MIT License

## 👤 Tác Giả

JLPT Learning App - 2026
