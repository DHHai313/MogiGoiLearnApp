package com.example.mogigoi.data.fake

import com.example.mogigoi.data.model.Lesson
import com.example.mogigoi.data.model.Level
import com.example.mogigoi.data.model.Vocabulary

/**
 * FakeDataSource - Nguồn dữ liệu giả cho ứng dụng học từ vựng JLPT.
 * Chứa 5 cấp độ × 5 bài × 10 từ = 250 từ mẫu.
 *
 * ─── Hướng dẫn chuyển sang Room Database ───
 * 1. Tạo @Entity cho Level, Lesson, Vocabulary
 * 2. Tạo DAO interface với các query tương ứng
 * 3. Implement VocabularyRepository bằng Room DAO
 * 4. Inject RoomVocabularyRepository thay FakeVocabularyRepository
 * 5. Không cần thay đổi ViewModel hay UI layer
 * ────────────────────────────────────────────
 */
object FakeDataSource {

    // ─────────────────────────────────────────────────────────────────
    // LEVELS
    // ─────────────────────────────────────────────────────────────────

    val levels: List<Level> = listOf(
        Level(
            id = "N5",
            name = "N5",
            description = "Cấp độ cơ bản — Từ vựng dành cho người mới bắt đầu",
            totalLessons = 5,
            totalWords = 50,
            learnedWords = 30
        ),
        Level(
            id = "N4",
            name = "N4",
            description = "Cấp độ sơ cấp — Từ vựng giao tiếp hàng ngày",
            totalLessons = 5,
            totalWords = 50,
            learnedWords = 15
        ),
        Level(
            id = "N3",
            name = "N3",
            description = "Cấp độ trung cấp — Từ vựng nâng cao",
            totalLessons = 5,
            totalWords = 50,
            learnedWords = 5
        ),
        Level(
            id = "N2",
            name = "N2",
            description = "Cấp độ cao — Từ vựng chuyên sâu",
            totalLessons = 5,
            totalWords = 50,
            learnedWords = 0
        ),
        Level(
            id = "N1",
            name = "N1",
            description = "Cấp độ cao nhất — Từ vựng học thuật và chuyên ngành",
            totalLessons = 5,
            totalWords = 50,
            learnedWords = 0
        )
    )

    // ─────────────────────────────────────────────────────────────────
    // LESSONS
    // ─────────────────────────────────────────────────────────────────

    val lessons: List<Lesson> = listOf(
        // N5
        Lesson(id = 1,  levelId = "N5", title = "Bài 1", topic = "Chào hỏi",       totalWords = 10, learnedWords = 10),
        Lesson(id = 2,  levelId = "N5", title = "Bài 2", topic = "Gia đình",        totalWords = 10, learnedWords = 10),
        Lesson(id = 3,  levelId = "N5", title = "Bài 3", topic = "Trường học",      totalWords = 10, learnedWords = 10),
        Lesson(id = 4,  levelId = "N5", title = "Bài 4", topic = "Đồ ăn & Uống",   totalWords = 10, learnedWords = 0),
        Lesson(id = 5,  levelId = "N5", title = "Bài 5", topic = "Giao thông",      totalWords = 10, learnedWords = 0),

        // N4
        Lesson(id = 6,  levelId = "N4", title = "Bài 1", topic = "Công việc",       totalWords = 10, learnedWords = 10),
        Lesson(id = 7,  levelId = "N4", title = "Bài 2", topic = "Mua sắm",         totalWords = 10, learnedWords = 5),
        Lesson(id = 8,  levelId = "N4", title = "Bài 3", topic = "Sức khỏe",        totalWords = 10, learnedWords = 0),
        Lesson(id = 9,  levelId = "N4", title = "Bài 4", topic = "Thời tiết",       totalWords = 10, learnedWords = 0),
        Lesson(id = 10, levelId = "N4", title = "Bài 5", topic = "Du lịch",         totalWords = 10, learnedWords = 0),

        // N3
        Lesson(id = 11, levelId = "N3", title = "Bài 1", topic = "Cảm xúc",         totalWords = 10, learnedWords = 5),
        Lesson(id = 12, levelId = "N3", title = "Bài 2", topic = "Xã hội",           totalWords = 10, learnedWords = 0),
        Lesson(id = 13, levelId = "N3", title = "Bài 3", topic = "Kinh tế",          totalWords = 10, learnedWords = 0),
        Lesson(id = 14, levelId = "N3", title = "Bài 4", topic = "Văn hoá",          totalWords = 10, learnedWords = 0),
        Lesson(id = 15, levelId = "N3", title = "Bài 5", topic = "Tự nhiên",         totalWords = 10, learnedWords = 0),

        // N2
        Lesson(id = 16, levelId = "N2", title = "Bài 1", topic = "Chính trị",        totalWords = 10, learnedWords = 0),
        Lesson(id = 17, levelId = "N2", title = "Bài 2", topic = "Khoa học",          totalWords = 10, learnedWords = 0),
        Lesson(id = 18, levelId = "N2", title = "Bài 3", topic = "Nghệ thuật",        totalWords = 10, learnedWords = 0),
        Lesson(id = 19, levelId = "N2", title = "Bài 4", topic = "Luật pháp",         totalWords = 10, learnedWords = 0),
        Lesson(id = 20, levelId = "N2", title = "Bài 5", topic = "Triết học",         totalWords = 10, learnedWords = 0),

        // N1
        Lesson(id = 21, levelId = "N1", title = "Bài 1", topic = "Văn học",           totalWords = 10, learnedWords = 0),
        Lesson(id = 22, levelId = "N1", title = "Bài 2", topic = "Tâm lý học",        totalWords = 10, learnedWords = 0),
        Lesson(id = 23, levelId = "N1", title = "Bài 3", topic = "Kiến trúc",         totalWords = 10, learnedWords = 0),
        Lesson(id = 24, levelId = "N1", title = "Bài 4", topic = "Y học",             totalWords = 10, learnedWords = 0),
        Lesson(id = 25, levelId = "N1", title = "Bài 5", topic = "Thiên văn học",     totalWords = 10, learnedWords = 0)
    )

    // ─────────────────────────────────────────────────────────────────
    // VOCABULARY — 250 từ
    // ─────────────────────────────────────────────────────────────────

    val vocabulary: List<Vocabulary> = listOf(

        // ═══════════════════════════════════════════
        // N5 — Bài 1: Chào hỏi (lesson_id = 1)
        // ═══════════════════════════════════════════
        Vocabulary(1, 1, "N5", "こんにちは", "こんにちは", "Xin chào (ban ngày)",
            "こんにちは、お元気ですか？", "Xin chào, bạn có khỏe không?"),
        Vocabulary(2, 1, "N5", "ありがとう", "ありがとう", "Cảm ơn",
            "ありがとうございます。", "Cảm ơn bạn rất nhiều."),
        Vocabulary(3, 1, "N5", "すみません", "すみません", "Xin lỗi / Xin phép",
            "すみません、トイレはどこですか？", "Xin lỗi, nhà vệ sinh ở đâu?"),
        Vocabulary(4, 1, "N5", "はじめまして", "はじめまして", "Rất vui được gặp bạn",
            "はじめまして、田中です。", "Rất vui được gặp bạn, tôi là Tanaka."),
        Vocabulary(5, 1, "N5", "おはよう", "おはよう", "Chào buổi sáng",
            "おはようございます！", "Chào buổi sáng!"),
        Vocabulary(6, 1, "N5", "さようなら", "さようなら", "Tạm biệt",
            "さようなら、また明日！", "Tạm biệt, hẹn gặp lại ngày mai!"),
        Vocabulary(7, 1, "N5", "はい", "はい", "Vâng / Đúng",
            "はい、わかりました。", "Vâng, tôi hiểu rồi."),
        Vocabulary(8, 1, "N5", "いいえ", "いいえ", "Không",
            "いいえ、ちがいます。", "Không, không phải vậy."),
        Vocabulary(9, 1, "N5", "おねがい", "おねがい", "Xin / Nhờ",
            "おねがいします。", "Xin hãy giúp tôi."),
        Vocabulary(10, 1, "N5", "どうぞ", "どうぞ", "Xin mời / Vui lòng",
            "どうぞ、お入りください。", "Xin mời, hãy vào đi."),

        // ═══════════════════════════════════════════
        // N5 — Bài 2: Gia đình (lesson_id = 2)
        // ═══════════════════════════════════════════
        Vocabulary(11, 2, "N5", "家族", "かぞく", "Gia đình",
            "私の家族は四人です。", "Gia đình tôi có bốn người."),
        Vocabulary(12, 2, "N5", "父", "ちち", "Bố (nhà mình)",
            "父は会社員です。", "Bố tôi là nhân viên công ty."),
        Vocabulary(13, 2, "N5", "母", "はは", "Mẹ (nhà mình)",
            "母は料理が上手です。", "Mẹ tôi nấu ăn rất giỏi."),
        Vocabulary(14, 2, "N5", "兄", "あに", "Anh trai (nhà mình)",
            "兄は大学生です。", "Anh trai tôi là sinh viên đại học."),
        Vocabulary(15, 2, "N5", "姉", "あね", "Chị gái (nhà mình)",
            "姉は結婚しています。", "Chị gái tôi đã kết hôn."),
        Vocabulary(16, 2, "N5", "弟", "おとうと", "Em trai",
            "弟は小学生です。", "Em trai tôi đang học tiểu học."),
        Vocabulary(17, 2, "N5", "妹", "いもうと", "Em gái",
            "妹はかわいいです。", "Em gái tôi rất đáng yêu."),
        Vocabulary(18, 2, "N5", "子供", "こども", "Trẻ em / Con cái",
            "子供が公園で遊んでいます。", "Trẻ em đang chơi ở công viên."),
        Vocabulary(19, 2, "N5", "祖父", "そふ", "Ông nội/ngoại",
            "祖父は八十歳です。", "Ông tôi tám mươi tuổi."),
        Vocabulary(20, 2, "N5", "祖母", "そぼ", "Bà nội/ngoại",
            "祖母は料理が好きです。", "Bà tôi thích nấu ăn."),

        // ═══════════════════════════════════════════
        // N5 — Bài 3: Trường học (lesson_id = 3)
        // ═══════════════════════════════════════════
        Vocabulary(21, 3, "N5", "学校", "がっこう", "Trường học",
            "学校は九時に始まります。", "Trường học bắt đầu lúc 9 giờ."),
        Vocabulary(22, 3, "N5", "先生", "せんせい", "Giáo viên",
            "先生は親切です。", "Giáo viên rất tốt bụng."),
        Vocabulary(23, 3, "N5", "学生", "がくせい", "Học sinh / Sinh viên",
            "学生たちは図書館で勉強しています。", "Các học sinh đang học ở thư viện."),
        Vocabulary(24, 3, "N5", "本", "ほん", "Sách",
            "この本はおもしろいです。", "Cuốn sách này rất thú vị."),
        Vocabulary(25, 3, "N5", "勉強", "べんきょう", "Học tập",
            "毎日日本語を勉強します。", "Tôi học tiếng Nhật mỗi ngày."),
        Vocabulary(26, 3, "N5", "教室", "きょうしつ", "Lớp học",
            "教室は三階にあります。", "Lớp học ở tầng ba."),
        Vocabulary(27, 3, "N5", "試験", "しけん", "Kỳ thi",
            "明日試験があります。", "Ngày mai có kỳ thi."),
        Vocabulary(28, 3, "N5", "宿題", "しゅくだい", "Bài tập về nhà",
            "宿題を忘れました。", "Tôi quên bài tập về nhà."),
        Vocabulary(29, 3, "N5", "鉛筆", "えんぴつ", "Bút chì",
            "鉛筆で書いてください。", "Hãy viết bằng bút chì."),
        Vocabulary(30, 3, "N5", "図書館", "としょかん", "Thư viện",
            "図書館で本を借ります。", "Tôi mượn sách ở thư viện."),

        // ═══════════════════════════════════════════
        // N5 — Bài 4: Đồ ăn & Uống (lesson_id = 4)
        // ═══════════════════════════════════════════
        Vocabulary(31, 4, "N5", "食べる", "たべる", "Ăn",
            "毎日りんごを食べます。", "Tôi ăn táo mỗi ngày."),
        Vocabulary(32, 4, "N5", "飲む", "のむ", "Uống",
            "水を飲みます。", "Tôi uống nước."),
        Vocabulary(33, 4, "N5", "ご飯", "ごはん", "Cơm / Bữa ăn",
            "ご飯を食べましたか？", "Bạn đã ăn cơm chưa?"),
        Vocabulary(34, 4, "N5", "パン", "パン", "Bánh mì",
            "朝ごはんにパンを食べます。", "Tôi ăn bánh mì vào bữa sáng."),
        Vocabulary(35, 4, "N5", "魚", "さかな", "Cá",
            "魚が好きです。", "Tôi thích ăn cá."),
        Vocabulary(36, 4, "N5", "肉", "にく", "Thịt",
            "肉を焼きます。", "Tôi nướng thịt."),
        Vocabulary(37, 4, "N5", "野菜", "やさい", "Rau củ",
            "野菜は体にいいです。", "Rau củ rất tốt cho sức khỏe."),
        Vocabulary(38, 4, "N5", "お茶", "おちゃ", "Trà",
            "お茶を一杯いかがですか？", "Bạn có muốn uống một tách trà không?"),
        Vocabulary(39, 4, "N5", "牛乳", "ぎゅうにゅう", "Sữa bò",
            "毎朝牛乳を飲みます。", "Tôi uống sữa mỗi buổi sáng."),
        Vocabulary(40, 4, "N5", "卵", "たまご", "Trứng",
            "卵を三個買いました。", "Tôi đã mua ba quả trứng."),

        // ═══════════════════════════════════════════
        // N5 — Bài 5: Giao thông (lesson_id = 5)
        // ═══════════════════════════════════════════
        Vocabulary(41, 5, "N5", "電車", "でんしゃ", "Tàu điện",
            "電車で学校に行きます。", "Tôi đi học bằng tàu điện."),
        Vocabulary(42, 5, "N5", "バス", "バス", "Xe buýt",
            "バスが来ました。", "Xe buýt đến rồi."),
        Vocabulary(43, 5, "N5", "車", "くるま", "Xe ô tô",
            "父の車は赤いです。", "Xe của bố tôi màu đỏ."),
        Vocabulary(44, 5, "N5", "自転車", "じてんしゃ", "Xe đạp",
            "自転車で公園まで行きます。", "Tôi đạp xe đến công viên."),
        Vocabulary(45, 5, "N5", "駅", "えき", "Ga tàu",
            "駅はここから近いです。", "Ga tàu gần đây thôi."),
        Vocabulary(46, 5, "N5", "飛行機", "ひこうき", "Máy bay",
            "飛行機で東京へ行きます。", "Tôi đi Tokyo bằng máy bay."),
        Vocabulary(47, 5, "N5", "船", "ふね", "Tàu thuyền",
            "船で島に行きました。", "Tôi đi tàu đến hòn đảo."),
        Vocabulary(48, 5, "N5", "道", "みち", "Con đường",
            "この道をまっすぐ行ってください。", "Hãy đi thẳng con đường này."),
        Vocabulary(49, 5, "N5", "右", "みぎ", "Bên phải",
            "右に曲がってください。", "Xin hãy rẽ phải."),
        Vocabulary(50, 5, "N5", "左", "ひだり", "Bên trái",
            "左に病院があります。", "Có bệnh viện ở phía bên trái."),

        // ═══════════════════════════════════════════
        // N4 — Bài 1: Công việc (lesson_id = 6)
        // ═══════════════════════════════════════════
        Vocabulary(51, 6, "N4", "仕事", "しごと", "Công việc",
            "仕事が終わりました。", "Công việc đã xong rồi."),
        Vocabulary(52, 6, "N4", "会社", "かいしゃ", "Công ty",
            "会社は九時に始まります。", "Công ty bắt đầu lúc 9 giờ."),
        Vocabulary(53, 6, "N4", "社員", "しゃいん", "Nhân viên công ty",
            "彼は優秀な社員です。", "Anh ấy là nhân viên xuất sắc."),
        Vocabulary(54, 6, "N4", "上司", "じょうし", "Cấp trên",
            "上司に報告します。", "Tôi báo cáo lên cấp trên."),
        Vocabulary(55, 6, "N4", "同僚", "どうりょう", "Đồng nghiệp",
            "同僚と昼ごはんを食べました。", "Tôi ăn trưa cùng đồng nghiệp."),
        Vocabulary(56, 6, "N4", "残業", "ざんぎょう", "Làm thêm giờ",
            "今日は残業があります。", "Hôm nay tôi phải làm thêm giờ."),
        Vocabulary(57, 6, "N4", "給料", "きゅうりょう", "Lương",
            "来月から給料が上がります。", "Từ tháng sau lương tôi sẽ tăng."),
        Vocabulary(58, 6, "N4", "会議", "かいぎ", "Cuộc họp",
            "午後に会議があります。", "Buổi chiều có cuộc họp."),
        Vocabulary(59, 6, "N4", "出張", "しゅっちょう", "Công tác (đi xa)",
            "来週大阪に出張します。", "Tuần tới tôi công tác ở Osaka."),
        Vocabulary(60, 6, "N4", "退社", "たいしゃ", "Tan làm / Nghỉ việc",
            "六時に退社します。", "Tôi tan làm lúc 6 giờ."),

        // ═══════════════════════════════════════════
        // N4 — Bài 2: Mua sắm (lesson_id = 7)
        // ═══════════════════════════════════════════
        Vocabulary(61, 7, "N4", "値段", "ねだん", "Giá cả",
            "この値段は高いですか？", "Giá này có đắt không?"),
        Vocabulary(62, 7, "N4", "割引", "わりびき", "Giảm giá",
            "今日は二割引です。", "Hôm nay giảm 20%."),
        Vocabulary(63, 7, "N4", "領収書", "りょうしゅうしょ", "Hóa đơn / Biên lai",
            "領収書をください。", "Xin cho tôi hóa đơn."),
        Vocabulary(64, 7, "N4", "返品", "へんぴん", "Trả hàng",
            "これを返品したいです。", "Tôi muốn trả lại cái này."),
        Vocabulary(65, 7, "N4", "お釣り", "おつり", "Tiền thối lại",
            "お釣りは五百円です。", "Tiền thối lại là 500 yên."),
        Vocabulary(66, 7, "N4", "支払い", "しはらい", "Thanh toán",
            "カードで支払いができますか？", "Có thể thanh toán bằng thẻ không?"),
        Vocabulary(67, 7, "N4", "品物", "しなもの", "Hàng hóa",
            "品物を確認してください。", "Xin hãy kiểm tra hàng hóa."),
        Vocabulary(68, 7, "N4", "買い物", "かいもの", "Mua sắm",
            "週末に買い物に行きます。", "Cuối tuần tôi đi mua sắm."),
        Vocabulary(69, 7, "N4", "売り場", "うりば", "Gian hàng / Khu bán",
            "売り場はどこですか？", "Gian hàng ở đâu vậy?"),
        Vocabulary(70, 7, "N4", "試着", "しちゃく", "Thử đồ",
            "試着してもいいですか？", "Tôi có thể thử đồ không?"),

        // ═══════════════════════════════════════════
        // N4 — Bài 3: Sức khỏe (lesson_id = 8)
        // ═══════════════════════════════════════════
        Vocabulary(71, 8, "N4", "病院", "びょういん", "Bệnh viện",
            "病院に行きます。", "Tôi đi bệnh viện."),
        Vocabulary(72, 8, "N4", "医者", "いしゃ", "Bác sĩ",
            "医者に診てもらいました。", "Tôi đã được bác sĩ khám."),
        Vocabulary(73, 8, "N4", "薬", "くすり", "Thuốc",
            "この薬を一日三回飲んでください。", "Hãy uống thuốc này 3 lần mỗi ngày."),
        Vocabulary(74, 8, "N4", "熱", "ねつ", "Sốt / Nhiệt độ",
            "熱が三十八度あります。", "Tôi bị sốt 38 độ."),
        Vocabulary(75, 8, "N4", "頭痛", "ずつう", "Đau đầu",
            "頭痛がひどいです。", "Tôi bị đau đầu nặng."),
        Vocabulary(76, 8, "N4", "風邪", "かぜ", "Cảm lạnh",
            "風邪をひきました。", "Tôi bị cảm lạnh rồi."),
        Vocabulary(77, 8, "N4", "手術", "しゅじゅつ", "Phẫu thuật",
            "手術は成功しました。", "Phẫu thuật đã thành công."),
        Vocabulary(78, 8, "N4", "注射", "ちゅうしゃ", "Tiêm",
            "注射が怖いです。", "Tôi sợ tiêm."),
        Vocabulary(79, 8, "N4", "休む", "やすむ", "Nghỉ ngơi",
            "ゆっくり休んでください。", "Hãy nghỉ ngơi thật nhiều."),
        Vocabulary(80, 8, "N4", "健康", "けんこう", "Sức khỏe",
            "健康が一番大切です。", "Sức khỏe là quan trọng nhất."),

        // ═══════════════════════════════════════════
        // N4 — Bài 4: Thời tiết (lesson_id = 9)
        // ═══════════════════════════════════════════
        Vocabulary(81, 9, "N4", "天気", "てんき", "Thời tiết",
            "今日の天気はどうですか？", "Thời tiết hôm nay thế nào?"),
        Vocabulary(82, 9, "N4", "晴れ", "はれ", "Trời nắng",
            "明日は晴れるでしょう。", "Ngày mai trời có lẽ sẽ nắng."),
        Vocabulary(83, 9, "N4", "雨", "あめ", "Mưa",
            "外は雨が降っています。", "Bên ngoài đang mưa."),
        Vocabulary(84, 9, "N4", "雪", "ゆき", "Tuyết",
            "北海道では雪が多いです。", "Ở Hokkaido tuyết rơi nhiều."),
        Vocabulary(85, 9, "N4", "風", "かぜ", "Gió",
            "今日は風が強いです。", "Hôm nay gió rất mạnh."),
        Vocabulary(86, 9, "N4", "台風", "たいふう", "Bão",
            "台風が来ています。", "Bão đang đến."),
        Vocabulary(87, 9, "N4", "気温", "きおん", "Nhiệt độ không khí",
            "今日の気温は三十度です。", "Nhiệt độ hôm nay là 30 độ."),
        Vocabulary(88, 9, "N4", "湿気", "しっけ", "Độ ẩm",
            "夏は湿気が高いです。", "Mùa hè độ ẩm rất cao."),
        Vocabulary(89, 9, "N4", "曇り", "くもり", "Trời nhiều mây",
            "今日は曇りです。", "Hôm nay trời nhiều mây."),
        Vocabulary(90, 9, "N4", "予報", "よほう", "Dự báo",
            "天気予報を見ました。", "Tôi đã xem dự báo thời tiết."),

        // ═══════════════════════════════════════════
        // N4 — Bài 5: Du lịch (lesson_id = 10)
        // ═══════════════════════════════════════════
        Vocabulary(91, 10, "N4", "旅行", "りょこう", "Du lịch",
            "来月旅行に行きます。", "Tháng tới tôi đi du lịch."),
        Vocabulary(92, 10, "N4", "ホテル", "ホテル", "Khách sạn",
            "ホテルを予約しました。", "Tôi đã đặt phòng khách sạn."),
        Vocabulary(93, 10, "N4", "観光", "かんこう", "Tham quan / Du lịch",
            "東京を観光します。", "Tôi tham quan Tokyo."),
        Vocabulary(94, 10, "N4", "地図", "ちず", "Bản đồ",
            "地図を持ってきましたか？", "Bạn có mang bản đồ không?"),
        Vocabulary(95, 10, "N4", "パスポート", "パスポート", "Hộ chiếu",
            "パスポートを忘れました。", "Tôi quên hộ chiếu rồi."),
        Vocabulary(96, 10, "N4", "両替", "りょうがえ", "Đổi tiền",
            "両替したいです。", "Tôi muốn đổi tiền."),
        Vocabulary(97, 10, "N4", "土産", "みやげ", "Quà lưu niệm",
            "土産を買いました。", "Tôi đã mua quà lưu niệm."),
        Vocabulary(98, 10, "N4", "案内", "あんない", "Hướng dẫn",
            "観光案内所に行きましょう。", "Hãy đến trung tâm thông tin du lịch."),
        Vocabulary(99, 10, "N4", "予約", "よやく", "Đặt trước / Đặt chỗ",
            "レストランを予約しました。", "Tôi đã đặt bàn ở nhà hàng."),
        Vocabulary(100, 10, "N4", "出発", "しゅっぱつ", "Khởi hành",
            "明日の朝、出発します。", "Sáng mai tôi sẽ khởi hành."),

        // ═══════════════════════════════════════════
        // N3 — Bài 1: Cảm xúc (lesson_id = 11)
        // ═══════════════════════════════════════════
        Vocabulary(101, 11, "N3", "感情", "かんじょう", "Cảm xúc",
            "感情をコントロールするのが大切です。", "Việc kiểm soát cảm xúc rất quan trọng."),
        Vocabulary(102, 11, "N3", "喜ぶ", "よろこぶ", "Vui mừng",
            "彼女はプレゼントをもらって喜びました。", "Cô ấy vui mừng khi nhận được quà."),
        Vocabulary(103, 11, "N3", "悲しむ", "かなしむ", "Buồn",
            "別れを悲しんでいます。", "Tôi buồn vì chia tay."),
        Vocabulary(104, 11, "N3", "怒る", "おこる", "Tức giận",
            "彼はすぐ怒ります。", "Anh ấy dễ nổi giận lắm."),
        Vocabulary(105, 11, "N3", "不安", "ふあん", "Lo lắng / Bất an",
            "試験前に不安を感じます。", "Tôi cảm thấy lo lắng trước kỳ thi."),
        Vocabulary(106, 11, "N3", "緊張", "きんちょう", "Hồi hộp / Căng thẳng",
            "発表で緊張しました。", "Tôi hồi hộp khi thuyết trình."),
        Vocabulary(107, 11, "N3", "驚く", "おどろく", "Kinh ngạc",
            "突然の知らせに驚きました。", "Tôi kinh ngạc trước tin tức bất ngờ."),
        Vocabulary(108, 11, "N3", "安心", "あんしん", "Yên tâm / An tâm",
            "無事に帰って安心しました。", "Tôi yên tâm khi về nhà an toàn."),
        Vocabulary(109, 11, "N3", "後悔", "こうかい", "Hối hận / Tiếc nuối",
            "あの時の決断を後悔しています。", "Tôi hối hận về quyết định hồi đó."),
        Vocabulary(110, 11, "N3", "希望", "きぼう", "Hy vọng",
            "いつも希望を持ってください。", "Hãy luôn giữ hy vọng."),

        // ═══════════════════════════════════════════
        // N3 — Bài 2: Xã hội (lesson_id = 12)
        // ═══════════════════════════════════════════
        Vocabulary(111, 12, "N3", "社会", "しゃかい", "Xã hội",
            "現代社会は複雑です。", "Xã hội hiện đại rất phức tạp."),
        Vocabulary(112, 12, "N3", "政府", "せいふ", "Chính phủ",
            "政府は新しい法律を作りました。", "Chính phủ đã ban hành luật mới."),
        Vocabulary(113, 12, "N3", "選挙", "せんきょ", "Bầu cử",
            "来月選挙があります。", "Tháng tới có bầu cử."),
        Vocabulary(114, 12, "N3", "権利", "けんり", "Quyền lợi",
            "人は平等な権利を持っています。", "Mọi người đều có quyền lợi bình đẳng."),
        Vocabulary(115, 12, "N3", "義務", "ぎむ", "Nghĩa vụ",
            "税金を払うのは国民の義務です。", "Nộp thuế là nghĩa vụ của công dân."),
        Vocabulary(116, 12, "N3", "問題", "もんだい", "Vấn đề",
            "環境問題が深刻です。", "Vấn đề môi trường rất nghiêm trọng."),
        Vocabulary(117, 12, "N3", "解決", "かいけつ", "Giải quyết",
            "問題を解決する方法を考えます。", "Tôi suy nghĩ cách giải quyết vấn đề."),
        Vocabulary(118, 12, "N3", "影響", "えいきょう", "Ảnh hưởng",
            "SNSは社会に大きな影響を与えます。", "Mạng xã hội có ảnh hưởng lớn đến xã hội."),
        Vocabulary(119, 12, "N3", "変化", "へんか", "Thay đổi",
            "社会は急速に変化しています。", "Xã hội đang thay đổi nhanh chóng."),
        Vocabulary(120, 12, "N3", "支援", "しえん", "Hỗ trợ / Ủng hộ",
            "困っている人を支援します。", "Tôi hỗ trợ những người đang gặp khó khăn."),

        // ═══════════════════════════════════════════
        // N3 — Bài 3: Kinh tế (lesson_id = 13)
        // ═══════════════════════════════════════════
        Vocabulary(121, 13, "N3", "経済", "けいざい", "Kinh tế",
            "日本の経済は回復しています。", "Kinh tế Nhật Bản đang phục hồi."),
        Vocabulary(122, 13, "N3", "貿易", "ぼうえき", "Thương mại",
            "日本は多くの国と貿易しています。", "Nhật Bản thương mại với nhiều quốc gia."),
        Vocabulary(123, 13, "N3", "投資", "とうし", "Đầu tư",
            "株式に投資しています。", "Tôi đầu tư vào cổ phiếu."),
        Vocabulary(124, 13, "N3", "利益", "りえき", "Lợi nhuận",
            "会社の利益が増えました。", "Lợi nhuận công ty đã tăng."),
        Vocabulary(125, 13, "N3", "損失", "そんしつ", "Tổn thất / Thiệt hại",
            "大きな損失を出しました。", "Chúng tôi đã chịu tổn thất lớn."),
        Vocabulary(126, 13, "N3", "予算", "よさん", "Ngân sách",
            "予算が足りません。", "Ngân sách không đủ."),
        Vocabulary(127, 13, "N3", "物価", "ぶっか", "Giá cả hàng hóa",
            "最近物価が上がっています。", "Gần đây giá cả hàng hóa đang tăng."),
        Vocabulary(128, 13, "N3", "景気", "けいき", "Tình hình kinh tế",
            "景気が良くなっています。", "Tình hình kinh tế đang tốt lên."),
        Vocabulary(129, 13, "N3", "輸出", "ゆしゅつ", "Xuất khẩu",
            "自動車を輸出しています。", "Chúng tôi xuất khẩu ô tô."),
        Vocabulary(130, 13, "N3", "輸入", "ゆにゅう", "Nhập khẩu",
            "石油を輸入しています。", "Chúng tôi nhập khẩu dầu mỏ."),

        // ═══════════════════════════════════════════
        // N3 — Bài 4: Văn hoá (lesson_id = 14)
        // ═══════════════════════════════════════════
        Vocabulary(131, 14, "N3", "文化", "ぶんか", "Văn hoá",
            "日本の文化に興味があります。", "Tôi quan tâm đến văn hoá Nhật Bản."),
        Vocabulary(132, 14, "N3", "伝統", "でんとう", "Truyền thống",
            "日本の伝統を守りたいです。", "Tôi muốn gìn giữ truyền thống Nhật Bản."),
        Vocabulary(133, 14, "N3", "習慣", "しゅうかん", "Tập tục / Thói quen",
            "日本には独特な習慣があります。", "Nhật Bản có những tập tục độc đáo."),
        Vocabulary(134, 14, "N3", "祭り", "まつり", "Lễ hội",
            "夏祭りは楽しいです。", "Lễ hội mùa hè rất vui."),
        Vocabulary(135, 14, "N3", "芸術", "げいじゅつ", "Nghệ thuật",
            "芸術は人の心を豊かにします。", "Nghệ thuật làm phong phú tâm hồn con người."),
        Vocabulary(136, 14, "N3", "武道", "ぶどう", "Võ đạo",
            "柔道は有名な武道です。", "Judo là một môn võ đạo nổi tiếng."),
        Vocabulary(137, 14, "N3", "茶道", "さどう", "Trà đạo",
            "茶道は日本の伝統文化です。", "Trà đạo là văn hoá truyền thống Nhật Bản."),
        Vocabulary(138, 14, "N3", "着物", "きもの", "Kimono",
            "成人式に着物を着ました。", "Tôi mặc kimono trong lễ trưởng thành."),
        Vocabulary(139, 14, "N3", "風習", "ふうしゅう", "Phong tục",
            "地域によって風習が違います。", "Phong tục khác nhau tùy theo vùng miền."),
        Vocabulary(140, 14, "N3", "民族", "みんぞく", "Dân tộc",
            "日本は単一民族の国です。", "Nhật Bản là đất nước đơn dân tộc."),

        // ═══════════════════════════════════════════
        // N3 — Bài 5: Tự nhiên (lesson_id = 15)
        // ═══════════════════════════════════════════
        Vocabulary(141, 15, "N3", "自然", "しぜん", "Tự nhiên",
            "自然を大切にしましょう。", "Hãy trân trọng thiên nhiên."),
        Vocabulary(142, 15, "N3", "環境", "かんきょう", "Môi trường",
            "環境を守る必要があります。", "Chúng ta cần bảo vệ môi trường."),
        Vocabulary(143, 15, "N3", "森林", "しんりん", "Rừng",
            "森林が減っています。", "Rừng đang bị thu hẹp."),
        Vocabulary(144, 15, "N3", "海洋", "かいよう", "Đại dương",
            "海洋汚染が問題です。", "Ô nhiễm đại dương là vấn đề lớn."),
        Vocabulary(145, 15, "N3", "地震", "じしん", "Động đất",
            "地震が多い国です。", "Đây là đất nước hay có động đất."),
        Vocabulary(146, 15, "N3", "火山", "かざん", "Núi lửa",
            "富士山は有名な火山です。", "Núi Phú Sĩ là ngọn núi lửa nổi tiếng."),
        Vocabulary(147, 15, "N3", "川", "かわ", "Sông",
            "川のそばで遊びました。", "Tôi chơi bên dòng sông."),
        Vocabulary(148, 15, "N3", "山", "やま", "Núi",
            "山に登りたいです。", "Tôi muốn leo núi."),
        Vocabulary(149, 15, "N3", "気候", "きこう", "Khí hậu",
            "日本の気候は四季があります。", "Khí hậu Nhật Bản có bốn mùa."),
        Vocabulary(150, 15, "N3", "資源", "しげん", "Tài nguyên",
            "天然資源を大切に使いましょう。", "Hãy sử dụng tài nguyên thiên nhiên một cách trân trọng."),

        // ═══════════════════════════════════════════
        // N2 — Bài 1: Chính trị (lesson_id = 16)
        // ═══════════════════════════════════════════
        Vocabulary(151, 16, "N2", "政治", "せいじ", "Chính trị",
            "政治に関心を持つことが大切です。", "Quan tâm đến chính trị là điều quan trọng."),
        Vocabulary(152, 16, "N2", "外交", "がいこう", "Ngoại giao",
            "外交関係を強化します。", "Chúng ta củng cố quan hệ ngoại giao."),
        Vocabulary(153, 16, "N2", "議会", "ぎかい", "Nghị viện",
            "議会で法案が審議されました。", "Dự luật được thảo luận ở nghị viện."),
        Vocabulary(154, 16, "N2", "条約", "じょうやく", "Hiệp ước",
            "両国は条約に署名しました。", "Hai nước đã ký hiệp ước."),
        Vocabulary(155, 16, "N2", "制度", "せいど", "Chế độ / Hệ thống",
            "教育制度を改革します。", "Chúng ta cải cách hệ thống giáo dục."),
        Vocabulary(156, 16, "N2", "憲法", "けんぽう", "Hiến pháp",
            "憲法に基づいた政治が必要です。", "Cần có nền chính trị dựa trên hiến pháp."),
        Vocabulary(157, 16, "N2", "民主主義", "みんしゅしゅぎ", "Dân chủ",
            "民主主義は国民の声を大切にします。", "Dân chủ tôn trọng tiếng nói của người dân."),
        Vocabulary(158, 16, "N2", "内閣", "ないかく", "Nội các",
            "新しい内閣が発足しました。", "Nội các mới đã được thành lập."),
        Vocabulary(159, 16, "N2", "首相", "しゅしょう", "Thủ tướng",
            "首相が記者会見を開きました。", "Thủ tướng đã mở cuộc họp báo."),
        Vocabulary(160, 16, "N2", "国際", "こくさい", "Quốc tế",
            "国際協力が必要です。", "Cần có sự hợp tác quốc tế."),

        // ═══════════════════════════════════════════
        // N2 — Bài 2: Khoa học (lesson_id = 17)
        // ═══════════════════════════════════════════
        Vocabulary(161, 17, "N2", "科学", "かがく", "Khoa học",
            "科学の進歩は著しいです。", "Sự tiến bộ của khoa học thật đáng kể."),
        Vocabulary(162, 17, "N2", "技術", "ぎじゅつ", "Công nghệ / Kỹ thuật",
            "先端技術を開発します。", "Chúng ta phát triển công nghệ tiên tiến."),
        Vocabulary(163, 17, "N2", "研究", "けんきゅう", "Nghiên cứu",
            "新薬の研究を続けています。", "Chúng tôi tiếp tục nghiên cứu thuốc mới."),
        Vocabulary(164, 17, "N2", "実験", "じっけん", "Thí nghiệm",
            "実験結果を分析します。", "Chúng tôi phân tích kết quả thí nghiệm."),
        Vocabulary(165, 17, "N2", "理論", "りろん", "Lý thuyết",
            "新しい理論を発表しました。", "Họ đã công bố lý thuyết mới."),
        Vocabulary(166, 17, "N2", "発明", "はつめい", "Phát minh",
            "偉大な発明が世界を変えました。", "Những phát minh vĩ đại đã thay đổi thế giới."),
        Vocabulary(167, 17, "N2", "人工知能", "じんこうちのう", "Trí tuệ nhân tạo",
            "人工知能の応用が広がっています。", "Ứng dụng của AI ngày càng mở rộng."),
        Vocabulary(168, 17, "N2", "宇宙", "うちゅう", "Vũ trụ",
            "宇宙の謎はまだ多い。", "Vũ trụ vẫn còn nhiều bí ẩn."),
        Vocabulary(169, 17, "N2", "遺伝子", "いでんし", "Gen / Di truyền",
            "遺伝子研究が進んでいます。", "Nghiên cứu gen đang tiến triển."),
        Vocabulary(170, 17, "N2", "分析", "ぶんせき", "Phân tích",
            "データを詳しく分析しました。", "Chúng tôi đã phân tích dữ liệu chi tiết."),

        // ═══════════════════════════════════════════
        // N2 — Bài 3: Nghệ thuật (lesson_id = 18)
        // ═══════════════════════════════════════════
        Vocabulary(171, 18, "N2", "芸能", "げいのう", "Nghệ thuật biểu diễn",
            "芸能界に入りたいです。", "Tôi muốn bước vào làng nghệ thuật."),
        Vocabulary(172, 18, "N2", "映画", "えいが", "Phim ảnh",
            "この映画は感動的です。", "Bộ phim này rất cảm động."),
        Vocabulary(173, 18, "N2", "音楽", "おんがく", "Âm nhạc",
            "音楽は心を癒します。", "Âm nhạc chữa lành tâm hồn."),
        Vocabulary(174, 18, "N2", "絵画", "かいが", "Hội họa",
            "美術館で絵画を鑑賞しました。", "Tôi thưởng thức hội họa tại bảo tàng."),
        Vocabulary(175, 18, "N2", "彫刻", "ちょうこく", "Điêu khắc",
            "この彫刻はすばらしいです。", "Tác phẩm điêu khắc này thật tuyệt vời."),
        Vocabulary(176, 18, "N2", "演劇", "えんげき", "Kịch nghệ",
            "演劇の公演を観に行きました。", "Tôi đã đi xem buổi biểu diễn kịch nghệ."),
        Vocabulary(177, 18, "N2", "文学", "ぶんがく", "Văn học",
            "日本文学の名作を読みました。", "Tôi đã đọc kiệt tác văn học Nhật Bản."),
        Vocabulary(178, 18, "N2", "創作", "そうさく", "Sáng tác",
            "小説を創作しています。", "Tôi đang sáng tác tiểu thuyết."),
        Vocabulary(179, 18, "N2", "鑑賞", "かんしょう", "Thưởng thức / Thẩm mỹ",
            "美しい景色を鑑賞します。", "Tôi thưởng thức cảnh đẹp."),
        Vocabulary(180, 18, "N2", "表現", "ひょうげん", "Biểu đạt / Biểu hiện",
            "感情を言葉で表現します。", "Tôi biểu đạt cảm xúc qua ngôn từ."),

        // ═══════════════════════════════════════════
        // N2 — Bài 4: Luật pháp (lesson_id = 19)
        // ═══════════════════════════════════════════
        Vocabulary(181, 19, "N2", "法律", "ほうりつ", "Luật pháp",
            "法律を守ることが大切です。", "Tuân thủ pháp luật là điều quan trọng."),
        Vocabulary(182, 19, "N2", "裁判", "さいばん", "Xét xử / Tòa án",
            "裁判で無罪になりました。", "Anh ấy được tòa án tuyên vô tội."),
        Vocabulary(183, 19, "N2", "弁護士", "べんごし", "Luật sư",
            "弁護士に相談しました。", "Tôi đã tư vấn với luật sư."),
        Vocabulary(184, 19, "N2", "犯罪", "はんざい", "Tội phạm",
            "犯罪を防ぐ対策が必要です。", "Cần có biện pháp phòng chống tội phạm."),
        Vocabulary(185, 19, "N2", "逮捕", "たいほ", "Bắt giữ",
            "容疑者が逮捕されました。", "Nghi phạm đã bị bắt giữ."),
        Vocabulary(186, 19, "N2", "刑罰", "けいばつ", "Hình phạt",
            "厳しい刑罰が科されました。", "Hình phạt nghiêm khắc đã được áp dụng."),
        Vocabulary(187, 19, "N2", "証拠", "しょうこ", "Bằng chứng",
            "証拠が見つかりました。", "Bằng chứng đã được tìm thấy."),
        Vocabulary(188, 19, "N2", "判決", "はんけつ", "Phán quyết",
            "判決が下されました。", "Phán quyết đã được đưa ra."),
        Vocabulary(189, 19, "N2", "訴訟", "そしょう", "Kiện tụng",
            "訴訟を起こしました。", "Chúng tôi đã khởi kiện."),
        Vocabulary(190, 19, "N2", "契約", "けいやく", "Hợp đồng",
            "契約書にサインしました。", "Tôi đã ký hợp đồng."),

        // ═══════════════════════════════════════════
        // N2 — Bài 5: Triết học (lesson_id = 20)
        // ═══════════════════════════════════════════
        Vocabulary(191, 20, "N2", "哲学", "てつがく", "Triết học",
            "哲学は人生の意味を考えます。", "Triết học suy ngẫm về ý nghĩa cuộc sống."),
        Vocabulary(192, 20, "N2", "倫理", "りんり", "Đạo đức / Luân lý",
            "医療倫理は重要な問題です。", "Đạo đức y tế là vấn đề quan trọng."),
        Vocabulary(193, 20, "N2", "価値観", "かちかん", "Quan niệm về giá trị",
            "人によって価値観が違います。", "Quan niệm về giá trị khác nhau tùy người."),
        Vocabulary(194, 20, "N2", "存在", "そんざい", "Tồn tại",
            "人間の存在意義を考えます。", "Tôi suy ngẫm về ý nghĩa tồn tại của con người."),
        Vocabulary(195, 20, "N2", "概念", "がいねん", "Khái niệm",
            "この概念を理解するのは難しいです。", "Thật khó để hiểu khái niệm này."),
        Vocabulary(196, 20, "N2", "矛盾", "むじゅん", "Mâu thuẫn",
            "発言に矛盾があります。", "Phát biểu có mâu thuẫn."),
        Vocabulary(197, 20, "N2", "本質", "ほんしつ", "Bản chất / Cốt lõi",
            "問題の本質を見極めます。", "Tôi nhìn ra bản chất của vấn đề."),
        Vocabulary(198, 20, "N2", "真理", "しんり", "Chân lý",
            "真理を追求することが哲学です。", "Triết học là việc theo đuổi chân lý."),
        Vocabulary(199, 20, "N2", "論理", "ろんり", "Logic",
            "論理的に考えてください。", "Hãy suy nghĩ một cách logic."),
        Vocabulary(200, 20, "N2", "認識", "にんしき", "Nhận thức",
            "問題への認識が大切です。", "Nhận thức về vấn đề là điều quan trọng."),

        // ═══════════════════════════════════════════
        // N1 — Bài 1: Văn học (lesson_id = 21)
        // ═══════════════════════════════════════════
        Vocabulary(201, 21, "N1", "叙述", "じょじゅつ", "Miêu tả / Trần thuật",
            "情景を詳細に叙述しました。", "Cảnh vật được miêu tả chi tiết."),
        Vocabulary(202, 21, "N1", "比喩", "ひゆ", "Ẩn dụ / So sánh",
            "詩には多くの比喩があります。", "Thơ ca có nhiều ẩn dụ."),
        Vocabulary(203, 21, "N1", "象徴", "しょうちょう", "Biểu tượng",
            "桜は日本の象徴です。", "Hoa anh đào là biểu tượng của Nhật Bản."),
        Vocabulary(204, 21, "N1", "韻律", "いんりつ", "Vần điệu / Nhịp điệu",
            "詩の韻律を学んでいます。", "Tôi đang học về vần điệu trong thơ."),
        Vocabulary(205, 21, "N1", "散文", "さんぶん", "Văn xuôi",
            "散文と詩の違いを説明します。", "Tôi giải thích sự khác biệt giữa văn xuôi và thơ."),
        Vocabulary(206, 21, "N1", "叙情", "じょじょう", "Trữ tình",
            "叙情的な表現が美しいです。", "Những biểu đạt trữ tình thật đẹp đẽ."),
        Vocabulary(207, 21, "N1", "擬人化", "ぎじんか", "Nhân cách hoá",
            "自然を擬人化した表現です。", "Đây là cách biểu đạt nhân cách hoá thiên nhiên."),
        Vocabulary(208, 21, "N1", "批評", "ひひょう", "Phê bình",
            "文学批評の論文を書きました。", "Tôi đã viết bài luận phê bình văn học."),
        Vocabulary(209, 21, "N1", "朗読", "ろうどく", "Đọc thành tiếng",
            "詩を朗読しました。", "Tôi đã đọc thơ thành tiếng."),
        Vocabulary(210, 21, "N1", "著者", "ちょしゃ", "Tác giả",
            "著者のサインをもらいました。", "Tôi đã xin chữ ký của tác giả."),

        // ═══════════════════════════════════════════
        // N1 — Bài 2: Tâm lý học (lesson_id = 22)
        // ═══════════════════════════════════════════
        Vocabulary(211, 22, "N1", "心理学", "しんりがく", "Tâm lý học",
            "心理学は人間の行動を研究します。", "Tâm lý học nghiên cứu hành vi con người."),
        Vocabulary(212, 22, "N1", "無意識", "むいしき", "Vô thức",
            "無意識の行動が多いです。", "Có nhiều hành động vô thức."),
        Vocabulary(213, 22, "N1", "認知", "にんち", "Nhận thức",
            "認知行動療法が効果的です。", "Liệu pháp nhận thức hành vi rất hiệu quả."),
        Vocabulary(214, 22, "N1", "潜在意識", "せんざいいしき", "Tiềm thức",
            "潜在意識が行動に影響します。", "Tiềm thức ảnh hưởng đến hành động."),
        Vocabulary(215, 22, "N1", "トラウマ", "トラウマ", "Chấn thương tâm lý",
            "トラウマを克服しました。", "Tôi đã vượt qua chấn thương tâm lý."),
        Vocabulary(216, 22, "N1", "共感", "きょうかん", "Đồng cảm",
            "相手に共感することが大切です。", "Đồng cảm với người khác là điều quan trọng."),
        Vocabulary(217, 22, "N1", "自己効力感", "じここうりょくかん", "Cảm giác tự hiệu quả",
            "自己効力感を高めましょう。", "Hãy nâng cao cảm giác tự hiệu quả của bạn."),
        Vocabulary(218, 22, "N1", "動機づけ", "どうきづけ", "Động lực / Thúc đẩy",
            "内的動機づけが重要です。", "Động lực nội tại rất quan trọng."),
        Vocabulary(219, 22, "N1", "ストレス", "ストレス", "Căng thẳng / Stress",
            "ストレスを適切に管理します。", "Tôi quản lý căng thẳng một cách phù hợp."),
        Vocabulary(220, 22, "N1", "適応", "てきおう", "Thích nghi",
            "新しい環境に適応しました。", "Tôi đã thích nghi với môi trường mới."),

        // ═══════════════════════════════════════════
        // N1 — Bài 3: Kiến trúc (lesson_id = 23)
        // ═══════════════════════════════════════════
        Vocabulary(221, 23, "N1", "建築", "けんちく", "Kiến trúc",
            "日本の伝統建築は美しいです。", "Kiến trúc truyền thống Nhật Bản rất đẹp."),
        Vocabulary(222, 23, "N1", "構造", "こうぞう", "Cấu trúc",
            "建物の構造を調査します。", "Chúng tôi điều tra cấu trúc của tòa nhà."),
        Vocabulary(223, 23, "N1", "設計", "せっけい", "Thiết kế",
            "新しいビルを設計しました。", "Tôi đã thiết kế tòa nhà mới."),
        Vocabulary(224, 23, "N1", "耐震", "たいしん", "Chống động đất",
            "耐震構造の建物が必要です。", "Cần có những tòa nhà kết cấu chống động đất."),
        Vocabulary(225, 23, "N1", "景観", "けいかん", "Cảnh quan",
            "街の景観を守ります。", "Chúng ta bảo vệ cảnh quan đô thị."),
        Vocabulary(226, 23, "N1", "素材", "そざい", "Vật liệu",
            "環境に優しい素材を使います。", "Chúng tôi sử dụng vật liệu thân thiện môi trường."),
        Vocabulary(227, 23, "N1", "施工", "せこう", "Thi công",
            "施工期間は半年です。", "Thời gian thi công là sáu tháng."),
        Vocabulary(228, 23, "N1", "外観", "がいかん", "Ngoại hình / Mặt ngoài",
            "建物の外観がユニークです。", "Ngoại hình của tòa nhà rất độc đáo."),
        Vocabulary(229, 23, "N1", "都市計画", "としけいかく", "Quy hoạch đô thị",
            "都市計画を見直す必要があります。", "Cần xem xét lại quy hoạch đô thị."),
        Vocabulary(230, 23, "N1", "修復", "しゅうふく", "Phục hồi / Trùng tu",
            "古い建物を修復しました。", "Chúng tôi đã trùng tu tòa nhà cổ."),

        // ═══════════════════════════════════════════
        // N1 — Bài 4: Y học (lesson_id = 24)
        // ═══════════════════════════════════════════
        Vocabulary(231, 24, "N1", "診断", "しんだん", "Chẩn đoán",
            "正確な診断が重要です。", "Chẩn đoán chính xác rất quan trọng."),
        Vocabulary(232, 24, "N1", "治療", "ちりょう", "Điều trị",
            "最新の治療法を導入します。", "Chúng tôi áp dụng phương pháp điều trị mới nhất."),
        Vocabulary(233, 24, "N1", "免疫", "めんえき", "Miễn dịch",
            "免疫力を高めましょう。", "Hãy tăng cường sức đề kháng."),
        Vocabulary(234, 24, "N1", "感染症", "かんせんしょう", "Bệnh truyền nhiễm",
            "感染症の予防が大切です。", "Phòng ngừa bệnh truyền nhiễm rất quan trọng."),
        Vocabulary(235, 24, "N1", "臨床", "りんしょう", "Lâm sàng",
            "臨床試験を行っています。", "Chúng tôi đang tiến hành thử nghiệm lâm sàng."),
        Vocabulary(236, 24, "N1", "病理", "びょうり", "Bệnh lý",
            "病理検査の結果が出ました。", "Kết quả xét nghiệm bệnh lý đã có."),
        Vocabulary(237, 24, "N1", "予防", "よぼう", "Phòng ngừa",
            "病気の予防が大切です。", "Phòng ngừa bệnh tật rất quan trọng."),
        Vocabulary(238, 24, "N1", "症状", "しょうじょう", "Triệu chứng",
            "症状を詳しく教えてください。", "Hãy mô tả chi tiết các triệu chứng."),
        Vocabulary(239, 24, "N1", "療養", "りょうよう", "Dưỡng bệnh / Điều dưỡng",
            "温泉で療養しました。", "Tôi dưỡng bệnh ở suối nước nóng."),
        Vocabulary(240, 24, "N1", "再生医療", "さいせいいりょう", "Y học tái tạo",
            "再生医療の研究が進んでいます。", "Nghiên cứu y học tái tạo đang tiến triển."),

        // ═══════════════════════════════════════════
        // N1 — Bài 5: Thiên văn học (lesson_id = 25)
        // ═══════════════════════════════════════════
        Vocabulary(241, 25, "N1", "天文学", "てんもんがく", "Thiên văn học",
            "天文学は宇宙を研究する学問です。", "Thiên văn học là bộ môn nghiên cứu vũ trụ."),
        Vocabulary(242, 25, "N1", "銀河", "ぎんが", "Thiên hà",
            "私たちは天の川銀河に住んでいます。", "Chúng ta sống trong dải Ngân Hà."),
        Vocabulary(243, 25, "N1", "惑星", "わくせい", "Hành tinh",
            "太陽系には八つの惑星があります。", "Hệ mặt trời có tám hành tinh."),
        Vocabulary(244, 25, "N1", "恒星", "こうせい", "Ngôi sao (hằng tinh)",
            "太陽は恒星の一つです。", "Mặt trời là một trong những hằng tinh."),
        Vocabulary(245, 25, "N1", "ブラックホール", "ブラックホール", "Hố đen",
            "ブラックホールの謎は深いです。", "Bí ẩn của hố đen rất sâu thẳm."),
        Vocabulary(246, 25, "N1", "重力", "じゅうりょく", "Trọng lực",
            "重力は物体を引きつけます。", "Trọng lực hút các vật thể lại."),
        Vocabulary(247, 25, "N1", "光年", "こうねん", "Năm ánh sáng",
            "最も近い星まで約四光年あります。", "Ngôi sao gần nhất cách khoảng 4 năm ánh sáng."),
        Vocabulary(248, 25, "N1", "星雲", "せいうん", "Tinh vân",
            "美しい星雲の写真を見ました。", "Tôi đã xem những bức ảnh tinh vân đẹp."),
        Vocabulary(249, 25, "N1", "宇宙探査", "うちゅうたんさ", "Thám hiểm vũ trụ",
            "宇宙探査機が火星に到着しました。", "Tàu thám hiểm vũ trụ đã đến sao Hỏa."),
        Vocabulary(250, 25, "N1", "天体観測", "てんたいかんそく", "Quan sát thiên thể",
            "天体観測が趣味です。", "Quan sát thiên thể là sở thích của tôi.")
    )

    // ─────────────────────────────────────────────────────────────────
    // HELPER FUNCTIONS
    // ─────────────────────────────────────────────────────────────────

    fun getLessonsByLevel(levelId: String): List<Lesson> =
        lessons.filter { it.levelId == levelId }

    fun getVocabularyByLesson(lessonId: Int): List<Vocabulary> =
        vocabulary.filter { it.lessonId == lessonId }

    fun getVocabularyByLevel(levelId: String): List<Vocabulary> =
        vocabulary.filter { it.levelId == levelId }
}
