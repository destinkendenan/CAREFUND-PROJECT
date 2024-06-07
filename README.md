## CareFund
CareFund adalah sebuah aplikasi donasi amal yang dirancang untuk memudahkan dalam proses donasi ke berbagai yayasan sosial dan kemanusiaan.
## Group Name : Anak Bugis
## Nama Anggota :
1. Destin Kendenan - H071231058
2. A. Muh. Fa’iqh Musharraf Reginald – H071231061
3. Andi Achmad Raihan – H071231077
## Tema yang dipilih : Social Impact and Volunteering
## Judul Proyek : CareFund
## Nama Pendamping : Kelvin Leonardo
## Tim Juri 
1. Muhammad Thoriq Ali Said
2. Muhammad Haerul
## Executive Summary 
Dalam masyarakat saat ini, banyak orang ingin berdonasi tetapi sering kali terhalang oleh kurangnya informasi tentang penerima yang tepat, serta kekhawatiran tentang transparansi penyaluran donasi.
Oleh karena itu, kami memilih untuk mengembangkan aplikasi “CareFund” sebagai solusi untuk masalah tersebut dengan memanfaatkan teknologi yang dapat mempermudah proses donasi dan menjamin bahwa donasi sampai ke tangan yang tepat. Aplikasi ini berfokus pada penyaluran donasi dalam bentuk uang.
Aplikasi “Carefund” dilengkapi sejumlah fitur, seperti profil pengguna yang memungkinkan setiap pengguna dapat mengelola profil mereka sendiri dan riwayat donasi. Selain itu, terdapat fitur pencarian organisasi amal untuk membantu pengguna menemukan organisasi amal sesuai dengan kriteria yang mereka inginkan, dan fitur metode pembayaran yang memungkinkan pengguna untuk menyumbangkan dana melalui berbagai metode pembayaran. Dengan fitur-fitur tersebut, CareFund tidak hanya mempermudah proses donasi, tetapi juga memastikan transparansi penyaluran donasi, serta memberikan pengalaman yang lebih baik dan terpercaya bagi para donatur.
## Fitur Aplikasi :
1. Login: Memungkinkan pengguna yang sudah terdaftar untuk masuk ke dalam aplikasi. Pengguna memasukkan username dan password di form login, kemudian sistem akan memeriksa data yang ada di database. Jika username dan password yang dimasukkan sesuai dengan yang ada di database, maka pengguna akan diarahkan ke halaman utama aplikasi. Jika tidak, sistem akan menampilkan pesan kesalahan.
2. Register: Memungkinkan pengguna baru untuk membuat akun di aplikasi. Pengguna mamasukkan informasi yang diperlukan seperti username, email dan password di form register. Kemudian sistem akan memeriksa apakah username atau email sudah digunakan oleh orang lain. Jika sudah ada, sistem akan menampilkan pesan kesalahan. Jika tidak, sistem akan menyimpan informasi pengguna baru ke database. Pada bagian textfieldnya email, program akan memeriksa validitas email menggunakan regex untuk memastikan email dalam format yang benar. Setelah registrasi berhasil, pengguna dapat login menggunakan username dan password yang baru dibuat.
3. Donasi: Memungkin pengguna untuk memberikan sumbangan berupa uang ke yayasan tertentu. Pada fitur ini, pengguna memilih yayasan yang ingin didonasikan, kemudia memasukkan jumlah donasi dan metode pembayaran. Pengguna akan menerima konfirmasi bahwa donasi telah berhasil.
4. Profil: Dapat menampilkan informasi terkait akun pengguna
5. Riwayat donasi: Dapat menampilkan daftar donasi yang telah dilakukan oleh pengguna. Sistem menampilkan riwayat donasi dari database yang terkait dengan pengguna tersebut
## Link Repository Github Project : [https://github.com/destinkendenan/CAREFUND-PROJECT]

## Penjelasan Penerapan Prinsip OOP 
1. Pada Parent.java, terdapat abstract class Parent yang yang memiliki 1 method abstract. (Abstraction)
2. Class DonationScene, HistoryScene, HomeScene, LoginScene, MainScene, ProfileScene, dan RegisterScene mewarisi class Parent. Setiap class tersebut meng-override 1 method abstract di class Parent. (Inheritance, Polymorphism)
3. Class History, User, dan App memiliki 3 private attribute. (Encapsulation).
## Mentoring 
- [Kelvin Leonardo] - [Rabu, 22/05/2024]
- [Kelvin Leonardo] - [Kamis, 23/05/2024]
- [Kelvin Leonardo] - [Jumat, 31/05/2024]
## Screenshots 
1. Home Scene![Screenshot (121)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/855c112f-9588-4244-aea2-de51d4ca3a94)

2. Login Scene![Screenshot (122)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/ce16bcad-ad12-49cd-833d-d34cc25a74f3)

3. Register Scene![Screenshot (125)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/d5e4f627-3a30-481b-a86c-bcea72cb3224)

4. Main Scene![Screenshot (123)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/1ddbe086-62e0-4e6d-9661-1cf58796346b)

5. Donation Scene![Screenshot (127)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/adacaeea-0d3c-47d2-8c29-0c815fc83360)

6. Profile Scene![Screenshot (124)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/8482aa43-4f65-47e4-a55b-d69bf545548a)

7. History Scene![Screenshot (126)](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/e85ad036-ba8a-448e-a78d-931c352efe5d)

## Pengujian Pada Aplikasi
![Screenshot 2024-06-08 001011](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/9a8f4724-846c-4479-ad88-e3e049630aec)
![Screenshot 2024-06-08 001033](https://github.com/destinkendenan/CAREFUND-PROJECT/assets/144416169/70c8c715-c998-4ac3-9fdc-4f619912142a)
