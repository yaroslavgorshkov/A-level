package ua.gorshkov.hw23.Ex1;

public class Main {
    /*
    Структура любого HTTP запроса состоит из таких основных частей: Start Line (состоит из метода запроса, адреса ресурса и тд),
    Headers (доп информация про запрос или про клиента), тело запроса (есть не везде; состоит из информации, которую
    клиент хочет отправить на сервер).
    Структура GET-запроса состоит только из Start Line и Headers, так как во время выполнения GET-запроса клиент не
    отправляет никаких данных на сервер.
    Структура POST-запроса состоит из всех 3 частей HTTP запроса. Тело POST-запроса должно включать информацию (параметры),
    которые клиент отправляет на сервер. Также нужно указать заголовок Content-Type, который определяет формат (JSON или XML)
    информации, которую клиент отправляет на сервер. Пример POST-запроса с Content-Type = JSON:
    curl -X POST -H "Content-Type: application/json" -d '{"k1": "v1", "k2": "v2"}' http://example.com
    Где запрос создан с помощью curl-утилиты, -X указывает тип запроса, -H - заголовки, -d - данные в теле запроса,
    http://example.com - адрес ресурса.
     */
}
