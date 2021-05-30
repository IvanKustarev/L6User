package Resources;

import java.util.ListResourceBundle;

public class Resources_ru extends ListResourceBundle implements Naming{

    private String name = "Русский";

    @Override
    public String getName() {
        return name;
    }

    private static final Object[][] contents =
            {
                    {"s1", "Привет"},
                    {"Консольная работа с командами", "Консольная работа с командами"},
                    {"Табличная работа с элементами", "Табличная работа с элементами"},
                    {"Область визуализации", "Область визуализации"},
                    {"User:", "Пользователь: "},
                    {"НАЗАД", "Назад"},
                    {"ip", "ip-адрес"},
                    {"Введены некорректные данные!", "Введены некорректные данные!"},
                    {"port", "порт"},
                    {"Ошибка подключения", "Ошибка подключения"},
                    {"CONNECT", "Подключиться"},
                    {"LOGIN", "Вход"},
                    {"REGISTER", "Регистрация"},

                    {"Попробуем снова...", "Попробуем снова..."},
                    {"Проблема с созданием порта!", "Проблема с созданием порта!"},
                    {"Проблема с подключением к серверу. Пробуем всё заново!", "Проблема с подключением к серверу. Пробуем всё заново!"},
                    {"Это что-то страшное... Попробуем ещё раз!", "Это что-то страшное... Попробуем ещё раз!"},
                    {"Нужно выбрать один из вариантов!", "Нужно выбрать один из вариантов!"},
                    {"Да", "Да"},
                    {"Нет", "Нет"},
                    {"Продолжаем попытки подключения к серверу?", "Продолжаем попытки подключения к серверу?"},
                    {"Завершаем работу!", "Завершаем работу!"},
                    {"Уведомление", "Уведомление"},

                    {"Login:", "Имя пользователя"},
                    {"Password:", "Пароль"},
                    {"Login", "Вход"},

                    {"Неверный пароль!", "Неверный пароль!"},
                    {"Сменить пользователя", "Сменить пользователя"},
                    {"Подключение", "Подключение"},
                    {"Ввод данных", "Ввод данных"},
                    {"Пользователь с таким логином уже существует.", "Пользователь с таким логином уже существует."},
                    {"OK", "Хорошо"},
                    {"Пользователь успешно добавлен", "Пользователь успешно добавлен"},
                    {"В главное меню", "В главное меню"},
                    {"Пользователя с таким логином не существует!", "Пользователя с таким логином не существует!"},



                    {"Введите команду сюда", "Введите команду сюда"},
                    {"Исполнить", "Исполнить"},
                    {"Для просмотра списка команд необходимо ввести \"help\"", "Для просмотра списка команд необходимо ввести \"help\""},
                    {"Введите фильтр", "Введите фильтр"},
                    {"Изменить", "Изменить"},
                    {"Выберете строку!", "Выберете строку!"},
                    {"Объект принадлежит другому пльзователю!", "Объект принадлежит другому пльзователю!"},
                    {"Удалить", "Удалить"},
                    {"Необходимо выбрать строку!", "Необходимо выбрать строку!"},
                    {"Сервер не отвечает!", "Сервер не отвечает!"},
                    {"Объект успешно удалён!", "Объект успешно удалён!"},
                    {"Имя", "Имя"},
                    {"Координата x", "Координата x"},
                    {"Координата y", "Координата y"},
                    {"Расположение", "Расположение"},
                    {"Количество комнат", "Количество комнат"},
                    {"Мебель", "Мебель"},
                    {"Вид", "Вид"},
                    {"Транспортные маршруты", "Транспортные маршруты"},
                    {"Имя дома", "Имя дома"},
                    {"Год пострйки дома", "Год пострйки дома"},
                    {"Количество этажей в доме", "Количество этажей в доме"},
                    {"Количество квартир на одном этаже", "Количество квартир на одном этаже"},
                    {"Количество лифтов", "Количество лифтов"},
                    {"Сохранить изменения", "Сохранить изменения"},
                    {"У квартиры обязательно должно быть имя", "У квартиры обязательно должно быть имя"},
                    {"У квартиры обязательно должна быть координата по X", "У квартиры обязательно должна быть координата по X"},
                    {"У квартиры обязательно должно быть координата по Y", "У квартиры обязательно должно быть координата по Y"},
                    {"У квартиры обязательно должно быть расположение", "У квартиры обязательно должно быть расположение"},
                    {"У квартиры обязательно должно быть количество комнат", "У квартиры обязательно должно быть количество комнат"},
                    {"У квартиры обязательно должна быть мебель", "У квартиры обязательно должна быть мебель"},
                    {"Все поля связанные с домом либо пустые, либо заполненные", "Все поля связанные с домом либо пустые, либо заполненные"},
                    {"Мебель задана некорректно!", "Мебель задана некорректно!"},
                    {"Транспорт задан некорректно!", "Транспорт задан некорректно!"},
                    {"Вид задан некорректно!", "Вид задан некорректно!"},
                    {"Потеряно соединение с сервером!", "Потеряно соединение с сервером!"},
                    {"Удаление прошло успешно.", "Удаление прошло успешно."},
                    {"Потеряно соединение с сервером!", "Потеряно соединение с сервером!"},
                    {"Объект успешно обновлён", "Объект успешно обновлён"},
                    {"В этих координатах нет квартир!", "В этих координатах нет квартир!"},
                    {"Объект принадлежит другому пользователю!", "Объект принадлежит другому пользователю!"},
                    {"Имя пользователя", "Имя пользователя"},
                    {"id", "id"},
                    {"Дата создания объекта", "Дата создания объекта"},
                    {"Имя квартиры", "Имя квартиры"},
                    {"Координата x", "Координата x"},
                    {"Координата y", "Координата y"},
                    {"Расположение", "Расположение"},
                    {"Количество комнат", "Количество комнат"},
                    {"Мебель", "Мебель"},
                    {"Вид", "Вид"},
                    {"поле пустое", "поле пустое"},
                    {"Транспортные маршруты", "Транспортные маршруты"},
                    {"поле пустое", "поле пустое"},
                    {"Имя дома", "Имя дома"},
                    {"поле пустое", "поле пустое"},
                    {"Год пострйки дома", "Год пострйки дома"},
                    {"поле пустое", "поле пустое"},
                    {"Количество этажей в доме", "Количество этажей в доме"},
                    {"поле пустое", "поле пустое"},
                    {"Количество квартир на одном этаже", "Количество квартир на одном этаже"},
                    {"поле пустое", "поле пустое"},
                    {"Количество лифтов", "Количество лифтов"},
                    {"поле пустое", "поле пустое"},
                    {"Имя дома", "Имя дома"},
                    {"Год пострйки дома", "Год пострйки дома"},
                    {"Количество этажей в доме", "Количество этажей в доме"},
                    {"Количество квартир на одном этаже", "Количество квартир на одном этаже"},
                    {"Количество лифтов", "Количество лифтов"},
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
