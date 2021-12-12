# Coursework_AP
Курсова робота з прикладного програмування. Виконав **Антоник Любомир**. Варіант №5

Що потрібно зробити для запуску цього проекту:
----------------------------------------------

0. Клонувати цей репозиторій собі на комп'ютер.

Для цього використайте 
>git clone https://github.com/Gofka81/Coursework-AP.git
>
---
1. Налаштувати свій гугл аканут для використання логеру в JavaMail API.
Посилання на гулг статтю -->[ТУТ](https://support.google.com/mail/answer/7126229?p=BadCredentials&visit_id=637749359848564636-4030175198&rd=2#cantsignin&zippy=)<-- (Виконати 2 перших пункти)

PS:Це потрібно для роботи JavaMail API, який буде надсилати з вашої пошти на іншу пошту помилки, які виникли в програмі.

---
2. Заповнити файл app.properties
Заповнити його в такому вигляді:
>TO = адреса_куди_надсилаємо@gmail.com
>
>FROM = адреса_звідки_надсилаємо@lpnu.ua
>
>password=пароль для FROM пошти
>
>connection.url = урл силка до вашої БД, ось наприклад моя : jdbc:mysql://localhost:3306/mpdb?user=root&password=root
---
3. Запустити скрипт **create-db.sql** У MySql.
---
4. Запустити **run.bat** 




Optional:
---------
Щоб додати якісь початкові дані в програму запустіть цей sql скрипт **insertSomeData.sql**  
