# Java-Technology-Midterm
## Giải thích dự án
- Dự án được thực hiện với tiêu chí xây dựng một trang web bán hàng online
- Dự án hiện đang được thực hiện dựa trển ngôn ngữ Java và Spring framwork, ngoài ra dự án còn được xây dựng dựa trên cấu trúc Reposiotry Pattern

## Giới thiệu cấu trúc dự án:
- Cấu trúc dự án được chia làm hai phần:
  - Phần 1: Thư mục tên java chính là thư mục chính của toàn bộ dự án nơi lưu trữ các gói package dùng cho việc xây dựng dự án
  - Phần 2: Thư mục tên resources chính là thư mục dùng để lưu trữ các tài nguyên nhu các file tính như css, js, hình ảnh đển các file HTML chứa giao diện của dự án và một file application.properties chuyên dùng để cấu hình môi trường.
 
## Cách chạy dự án:
- Để có thể tiến hành chạy dự án đầu tiên ta cần có các yêu cầu sau:
  - Yêu cấu cấu hình máy: Java phiên bản 17 trở lên, MySQL phiên bản mới nhất hoặc Xampp,
  - Các bước tiên hành để chạy dự án:
    - Bước 1: Xây dựng một schema có tên bất kì trên MySQL workbench hoặc tạo một cơ sở dữ liệu mới trên phpmyadmin
    - Bước 2: Cấu hình file application.properties bao gồm 
      - server.port = 3000: Thiêt lập cổng để chạy dự án
      - spring.datasource.url = jdbc:mysql://localhost:3306/{tên cơ sở dữ liệu} : Tạo một chuỗi kết nối đến cơ sở dữ liệu
      - spring.datasource.username = root : Tên tài khoản MySQL(có thể thay đổi dựa trên người dùng)
      - spring.datasource.password = root : Mật khẩu của MySQL(có thể thay đổi dựa trên người dùng)
      - spring.datasource.platform = mysql : Loại cơ sở dữ liệu
      - spring.jpa.hibernate.ddl-auto = create : Khi thiết lập cái này hệ thống sẽ tự động xóa đi cơ sở dữ liệu cũ để tạo mới cơ sở dữ liệu
      - upload.directory = src/main/resources/static/uploads: Thư mục để lưu trữ hình ảnh file do ta tự đặt ra
      - spring.security.user.name=kien : Tên tài khoản cho Spring Security (sẽ mặc định là user nếu không có)
      - spring.security.user.password=kien123 : Mật khẩu cho Spring Security (nếu không có thì hệ thống sẽ tự động tạo ra một key để thay thế khi chạy)
      - logging.level.org.hibernate.SQL = DEBUG : Điều này đặt mức ghi nhật ký cho các câu lệnh SQL được Hibernate thực thi thành DEBUG. Điều này có nghĩa là Hibernate sẽ ghi lại các câu lệnh SQL ở cấp độ DEBUG, cung cấp thông tin chi tiết hơn về các truy vấn SQL đang được thực thi.
      - logging.level.org.hibernate.Type = TRACE : Điều này đặt mức ghi nhật ký cho các loại Hibernate thành TRACE. Các kiểu ngủ đông xử lý cách các kiểu Java được ánh xạ tới các kiểu cơ sở dữ liệu. Đặt nó thành TRACE có nghĩa là ghi nhật ký chi tiết hơn cho các hoạt động liên quan.
## Các ảnh chụp Postman để xác mình API      
- Dưới đây là đường dẫn các hình ảnh xác mình API được thực hiện trên Postman
- https://drive.google.com/drive/folders/1Pz7mZO_Jf1tMyPeB0_8Q2872MU7ezAmR?usp=sharing





