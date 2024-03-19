Java Spring Boot - Veteriner Yönetim Sistemi Bitirme Projesi
Veteriner Yönetim Sistemi
Veteriner yönetim sistemi projesi ile bir veteriner kliniğinin kendi işlerini yönetebildiği API lar bulunan bir proje

Yazdığımız uygulama veteriner çalışanı tarafından kullanılacaktır. Bu uygulama ile çalışan sisteme

veteriner doktorları kaydedecek,

doktorların çalışma günlerini (müsait günlerini) kaydedecek, saat olmadan tarih olarak kayıt yapılacak,

müşterileri kaydedecek,

müşterilere ait hayvanları kaydedecek,

hayvanlara uygulanmış aşıları tarihleriyle birlikte kaydedecek,

hayvanlar için veteriner hekimlere randevu oluşturacaklar,

randevu oluştururken tarih ve saat girilecek,

randevu oluştururken hem doktorun müsait günlerinden saat olmadan kontrol yapılmalı hem de randevu kayıtlarından tarih ve saat ile kontrol yapılmalı. Kayıtlarda çakışma olmadığı durumda randevu oluşturulmalıdır.

Projenin veri tabanı dışa aktarılıp postgreSql dosyasının içine backup olarak atılmıştır. Aynı zamanda ENDPOİNTLER postgreSQL dosyasının içersinde mevcuttur.

Teknolojiler

Spring Boot: Uygulamanın temelini oluşturur.    

Spring Data JPA: Veritabanı işlemleri için kullanılır.    

MySQL & PostgreSQL: Veritabanı sistemleri.    

Lombok: Kod sadeliği için kullanılır.    

ModelMapper: Nesneler arası eşleştirme için.    

Spring Boot Validation: Girdi doğrulama işlemleri için.


ENDPOINTLER

PROJENİN ENDPOINTLERI :

Customer:

![Customer.png](postgreSql%2FENDPOINT%2FCustomer.png)

POST save http://localhost:8080/v1/customers,   
GET findAll  http://localhost:8080/v1/customers/all,   
GET findByID http://localhost:8080/v1/customers/6,      
PUT update http://localhost:8080/v1/customers,    
DELETE delete http://localhost:8080/v1/customers/7,     
GET findByName http://localhost:8080/v1/customers/name/İrfan


Vaccine:

![Vaccine.png](postgreSql%2FENDPOINT%2FVaccine.png)

POST save http://localhost:8080/v1/vaccines,   
GET findAll  http://localhost:8080/v1/vaccines/all,   
GET get http://localhost:8080/v1/vaccines/20,      
PUT update http://localhost:8080/v1/vaccines,    
DELETE delete http://localhost:8080/v1/vaccines/2,     
GET findByAnimalId http://localhost:8080/v1/vaccines/animal/15,   
GET protectionRange http://localhost:8080/v1/vaccines/by-protection-date-range?startDate=2023-01-01&finishDate=2024-03-30




AvailableDate:

![AvailableDate.png](postgreSql%2FENDPOINT%2FAvailableDate.png)

POST save http://localhost:8080/v1/availableDates,   
GET findAll  http://localhost:8080/v1/availableDates/all,   
GET findByID http://localhost:8080/v1/availableDates/1,      
PUT update http://localhost:8080/v1/availableDates,    
DELETE delete http://localhost:8080/v1/availableDates/5,     



Appointment: 

![Appointment.png](postgreSql%2FENDPOINT%2FAppointment.png)

POST save http://localhost:8080/v1/appointments,   
GET findAll  http://localhost:8080/v1/appointments/all,   
GET findByID http://localhost:8080/v1/appointments/13,      
PUT update http://localhost:8080/v1/appointments,    
DELETE delete http://localhost:8080/v1/customers/7,     
GET doctorRangeDate http://localhost:8080/v1/appointments/by-doctor-and-date-range?doctorId=8&startDate=2023-01-12T00:00&finishDate=2024-12-31T23:59,   
GET animalRangeDate http://localhost:8080/v1/appointments/by-animal-and-date-range?animalId=15&startDate=2023-01-01T14:00:00&finishDate=2024-12-23T14:00:00

Doctor:

![Doctor.png](postgreSql%2FENDPOINT%2FDoctor.png)

POST save http://localhost:8080/v1/doctors,   
GET findAll  http://localhost:8080/v1/doctors/all,   
GET findByID http://localhost:8080/v1/doctors/7,      
PUT update http://localhost:8080/v1/doctors,    
DELETE delete http://localhost:8080/v1/doctors/4,    


Animal:

![Animal.png](postgreSql%2FENDPOINT%2FAnimal.png)

POST save http://localhost:8080/v1/animals,   
GET findAll  http://localhost:8080/v1/animals/all,   
GET findByID http://localhost:8080/v1/animals/16,      
PUT update http://localhost:8080/v1/animals,    
DELETE delete http://localhost:8080/v1/animals/15,   
GET findByName http://localhost:8080/v1/animals/name/Zeytin   
GET findByCustomer http://localhost:8080/v1/animals/by-customer/6


![Ekran Resmi 2024-03-19 19.14.36.png](..%2F..%2F..%2FDesktop%2FEkran%20Resmi%202024-03-19%2019.14.36.png)





![umlGuncel.png](..%2F..%2F..%2FDesktop%2FumlGuncel.png)








