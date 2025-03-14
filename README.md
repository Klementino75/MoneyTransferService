## Курсовой проект: "Сервис перевода денег".

Необходимо разработать приложение - **_REST-сервис_**. Сервис должен предоставить интерфейс для перевода денег с одной карты на другую по заранее описанной спецификации (**_MoneyTransferServiceSpecification.yaml_**).
Заранее подготовленное веб-приложение (**_FRONT_**) должно подключаться к разработанному сервису без доработок и использовать его функционал для перевода денег.

### Требования к приложению

- Сервис должен предоставлять **_REST-интерфейс_** для интеграции с FRONT.
- Сервис должен реализовывать все методы перевода с одной банковской карты на другую, описанные в протоколе **_MoneyTransferServiceSpecification.yaml_**.
- Все изменения должны записываться в файл - лог переводов в произвольном формате с указанием:
даты; времени; карты, с которой было списание; карты зачисления; суммы; комиссии; результата операции, если был.

### Требования в реализации

- Разработать приложение с использованием **_Spring Boot_**.
- Использовать сборщик пакетов **_gradle/maven_**.
- Для запуска используются **_Docker_**, **_Docker Compose_**.
- Код разместить на **_GitHub_**.
- Код покрыть **_JUnit-тестами_** с использованием **_Mockito_**.
- Добавить интеграционные тесты с использованием **_TestContainers_**.

### Реализация

- Приложение разработано с использованием **_Spring Boot_**.
- Использован сборщик пакетов **_maven_**.
- Для запуска используются **_Docker_**, **_docker-compose.yaml_**.
- Код размещён на **_GitHub_**.
- Код покрыт **_JUnit-тестами_** с использованием **_Mockito_**.
- Добавлены интеграционные тесты с использованием **_TestContainers_**.

### Описание интеграции с FRONT

FRONT доступен по адресу https://github.com/serp-ya/card-transfer или в папкке **_card-transfer_**.
Можно скачать репозиторий или использовать уже развёрнутое демо-приложение по адресу https://serp-ya.github.io/card-transfer/ (тогда ваш API должен быть запущен по адресу http://localhost:5500/).

### Решение

Приложение представляет демо-версию простого сервиса перевода денежных средств с одной банковской карты на другую по описанной спецификации **_MoneyTransferServiceSpecification.yaml_**. 

Лог работы сервиса записывается в файл **_clientLog.log_** в папке **_resources_**.

### Запуска приложения

#### 1. Dockerfile

- Запускаем терминал и cобираем **_jar_** архив с нашим **_Spring Boot_** приложением: `./mvn clean package`
- Создаем образ из нашего Dockerfile, мы должны запустить: `docker build --tag=myapp .`
- Запускаем контейнер из нашего образа: `docker run --rm -p5500:5500 -it myapp`

#### 2. docker-compose.yaml

- Запускаем терминал и cобираем **_jar_** архив с нашим **_Spring Boot_** приложением: `./mvn clean package`
- В терминале и выполнить команду: `docker-compose up`

### Описание

Описание web-интерфейса можно посмотреть [по адресу](https://github.com/PoddubniySerg/MoneyTransferApp/blob/master/WebClient/card-transfer-master/README.md).

Сервер обрабатывает запросы от клиента и проверяются валидации:
- Номер карты - обязательное, минимум 16 знаков.
- ММ/ГГ - обязательное, минимум 5 знаков.
- CVC - обязательное, минимум 3 знака.
- Сумма перевода - обязательное, не может быть равное или меньше 0.
- Номер карты отправителя и карты получателя не должны совпадать.
- У карты получателя должен быть счет в валюте перевода.
- На счете отправителя достаточно средств для проведения операции.

### Проверка
1. Протестировать приложение в браузере: https://serp-ya.github.io/card-transfer/
2. Протестировать приложение с помощью **_curl / postman / idea_**:

- POST request -> http://localhost:5500/transfer
```
{
  "cardFromNumber": "1115776600101892",
  "cardFromValidTill": "05/25",
  "cardFromCVV": "530",
  "cardToNumber": "5555636255001321",
  "amount": {
    "value": 11000,
    "currency": "RUB"
  }
}
```

response -> 200 OK

```
{
  "operationId": "1"
}
```
response -> 400 and 500

```
{
  "message": "string",
  "id": 0
}
```

- POST request -> http://localhost:5500/confirmOperation

```
{
  "operationId": "1",
  "code": "2304"
}
```

response -> 200 OK
```
{
  "operationId": "1"
}
```
response -> 400 and 500
```
{
  "message": "string",
  "id": 0
}
```
