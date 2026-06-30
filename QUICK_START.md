# Quick Start - Hướng Dẫn Bắt Đầu Nhanh

## 🚀 5 Bước Để Bắt Đầu

### 1️⃣ Clone/Mở Dự Án

```bash
cd MogiGoi
```

### 2️⃣ Build Ứng Dụng

```bash
./gradlew clean build
```

### 3️⃣ Chạy Trên Emulator/Device

- Mở Android Studio
- Kết nối device hoặc chạy emulator
- Nhấn Run (Shift + F10)

### 4️⃣ Chọn Cấp Độ JLPT

Từ Home screen, chọn một trong 5 cấp độ:

- 🟦 **N5** - Cơ bản
- 🟩 **N4** - Sơ cấp
- 🟨 **N3** - Trung cấp
- 🟧 **N2** - Cao
- 🟥 **N1** - Cao nhất

### 5️⃣ Chọn Chế Độ Học

```
┌─────────────────────┐
│   🎴 FlashCard      │  ← Học từ từ, lật thẻ
├─────────────────────┤
│    📝 Quiz          │  ← Trắc nghiệm 4 đáp án
└─────────────────────┘
```

---

## 📚 Cấu Trúc Ứng Dụng

```
🏠 HOME (MainActivity)
  ↓
📋 CHOOSE LEVEL (Chọn cấp độ N5-N1)
  ↓
🎓 LEARNING MODE (Chọn chế độ học)
  ↙            ↘
🎴 FLASHCARD   📝 QUIZ
   │             │
   ├ Lật thẻ     ├ Chọn đáp án
   ├ Previous    ├ Phản hồi tức thì
   ├ Next        ├ Hiển thị đáp án đúng
   └ Shuffle     └ Tính điểm
```

---

## 🎮 Chế Độ FlashCard

**Giao Diện:**

```
┌─────────────────────────────────┐
│         Toolbar Title            │
├─────────────────────────────────┤
│              1/10                │ ← Tiến độ
├─────────────────────────────────┤
│  ┌───────────────────────────┐   │
│  │                           │   │
│  │        漢字               │   │ ← Nhấn để lật
│  │       (Kanji)            │   │
│  │                           │   │
│  └───────────────────────────┘   │
├─────────────────────────────────┤
│  ← Trước   🔀 Trộn    Sau →      │ ← Nút điều hướng
└─────────────────────────────────┘
```

**Các Nút:**

- **← Trước**: Về từ trước (nếu có)
- **Sau →**: Sang từ tiếp theo (nếu có)
- **🔀 Trộn**: Xáo trộn danh sách từ ngẫu nhiên

---

## 📝 Chế Độ Quiz

**Giao Diện:**

```
┌─────────────────────────────────┐
│         Toolbar Title            │
├─────────────────────────────────┤
│  1/10          ⭐ Đúng: 0/0      │ ← Tiến độ & Điểm
├─────────────────────────────────┤
│  ▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░│ ← Progress bar
├─────────────────────────────────┤
│  Chọn ý nghĩa của: 私 (わたし)   │
├─────────────────────────────────┤
│  [A] Tôi (chính thức)           │ ← Chọn 1 đáp án
│  [B] Anh ấy                      │
│  [C] Phụ nữ                      │
│  [D] Nam giới                    │
│                                  │
│  [ Câu tiếp theo →  ]            │ ← Nút next
└─────────────────────────────────┘
```

**Quy Tắc:**

- ✅ Đáp án đúng → Nút xanh
- ❌ Đáp án sai → Nút đỏ (hiển thị đáp án đúng)
- 📊 Tự động tính điểm

---

## 💾 Dữ Liệu Mẫu

Ứng dụng đã tích hợp 20 từ mẫu:

- **N5**: 10 từ (cơ bản)
- **N4**: 10 từ (sơ cấp)

Để thêm từ mới, xem: [VOCABULARY_GUIDE.md](VOCABULARY_GUIDE.md)

---

## 🎨 Giao Diện

### Màu Sắc Chính:

```
🔵 Xanh dương (#2196F3)    - Primary color, toolbar
🔴 Đỏ (#FF6B6B)           - Accent color, Quiz mode
💙 Xanh (#3498DB)         - FlashCard mặt trước
❤️ Đỏ (#E74C3C)           - FlashCard mặt sau
✅ Xanh lá (#27AE60)       - Câu trả lời đúng
❌ Đỏ (#E74C3C)           - Câu trả lời sai
```

---

## ⌨️ Shortcuts & Tips

| Hành Động     | Cách Thực Hiện           |
| ------------- | ------------------------ |
| Lật thẻ       | Nhấn vào CardView        |
| Từ trước      | Nhấn nút ←               |
| Từ sau        | Nhấn nút →               |
| Trộn từ       | Nhấn 🔀 Trộn             |
| Chọn đáp án   | Nhấn vào nút             |
| Câu tiếp theo | Nhấn nút Câu tiếp theo → |
| Quay lại      | Nhấn Back hoặc UP        |

---

## 📱 Thông Tin Hệ Thống

- **Tên ứng dụng**: JLPT Vocabulary
- **Phiên bản**: 1.0
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 15)
- **Ngôn ngữ**: Kotlin
- **Database**: SQLite

---

## 🐛 Troubleshooting

### ❌ Build thất bại

```bash
./gradlew clean build
```

### ❌ Ứng dụng không chạy

1. Kiểm tra Android Studio có SDK mới nhất
2. Kết nối lại device/emulator
3. Xóa project cache: `rm -rf .gradle/`

### ❌ Từ vựng không hiển thị

1. Xóa app data từ device
2. Rebuild & rerun

---

## 📞 Liên Hệ Hỗ Trợ

Nếu gặp vấn đề, tham khảo:

- [README.md](README.md) - Hướng dẫn chi tiết
- [VOCABULARY_GUIDE.md](VOCABULARY_GUIDE.md) - Thêm từ vựng

---

**Chúc bạn học tập hiệu quả! 🎌📚**

Happy Learning! 🚀
