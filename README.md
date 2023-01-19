# Команда разработчиков Group 5 - Team 1

В разработке проекта участвовали:

- [Голенев Алексей.](https://t.me/Alexey_Golenev)
- [Трофимов Федор.](https://t.me/Verlonar)
- [Шилов Кирилл.](https://t.me/sarataza)
- [Шкрил Никита.](https://t.me/Shkril95)

Каждый разработчик занимался созданием сущностей, сервисов, контроллеров, миграциями, мапперами и тестированием в своей
зоне ответственности.

Разработка осуществлялась с применением методологии Gitflow.

В качестве инструмента для управления проектом использовалась канбан-доска.

Для документирования использовался JavaDoc.
___

### В проекте реализованы следующие функции:

- логирование сервисов;
- регистрация пользователя;
- вход в учётную запись и выход из неё;
- обновление данных профиля (в т.ч. аватарки пользователя);
- смена пароля;
- добавление, редактирование, удаление объявлений;
- поиск по объявлениям;
- добавление, редактирование, удаление комментариев;
- загрузка изображений объявлений и аватарок пользователей.

--- 

### Запуск через Docker

Для полноценной работы необходимо загрузить образ и создать контейнер при помощи команды:

- docker load --input adsclientv16.tar

Для запуска фронтенда (с последующим удалением) необходимо ввести следующую команду:

- docker run --rm --name <имя_вашего_образа> -p3000:3000 adsclient:v16

### Используемые библиотеки

В ходе разработки были задействованы следующие библиотеки:

- Spring Framework:
  - spring boot starter data jpa;
  - spring boot starter validation;
  - spring boot starter web;
  - spring boot starter test;
  - spring boot starter core;
  - spring boot starter config;
  - spring boot starter web.
- Openapi.
- Postgresql.
- Mapstruct.
- Lombok.

### Важные примечания, выявленные в ходе тестирования взаимодействия бэкенд и фронтенд частей

- Если не отображаются изображения, необходимо выключить плагины браузера, которые могут блокировать сетевые данные.
