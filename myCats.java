import java.sql.*;

public class myCats {
    public static Connection con;
    public static Statement st;
    public static ResultSet resSet;
    public static void Conn() throws ClassNotFoundException, SQLException {
        //con = null;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
        System.out.println("База создана");
    }
    public static void CreateDB() throws SQLException {
        st = con.createStatement();
        st.execute("CREATE TABLE if not exists types (id INTEGER PRIMARY KEY AUTOINCREMENT, type VARCHAR(100) UNIQUE)");
        System.out.println("Таблица Типы создана");
    }

    public static void CreateDB_2() throws SQLException {
        st = con.createStatement();
        st.execute("CREATE TABLE if not exists cats (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) NOT NULL, type_id INTEGER NOT NULL, age INTEGER NOT NULL, weight DOUBLE," +
                "FOREIGN KEY(type_id) REFERENCES types (id))");
        System.out.println("Таблица Коты создана");
    }

    public static void CloseDB() throws SQLException {
        con.close();
        st.close();
    }

    //заполняем таблицу типы котов
    public static void InsertDB() throws SQLException {
        //st.execute("DELETE FROM types");
        String[] types = new String[]{"Абиссинская кошка","Австралийский мист","Американская жесткошерстная",                "Американская короткошерстная",               "Американский бобтейл",                "Американский кёрл",               "Балинезийская кошка",                "Бенгальская кошка",                "Бирманская кошка",                "Бомбейская кошка",                "Бразильская короткошёрстная",                "Британская длинношерстная",                "Британская короткошерстная",                "Бурманская кошка",                "Бурмилла кошка",                "Гавана",                "Гималайская кошка",                "Девон-рекс",                "Донской сфинкс",                "Европейская короткошерстная",                "Египетская мау",                "Канадский сфинкс",                "Кимрик",                "Корат",                "Корниш-рекс",                "Курильский бобтейл",                "Лаперм",              "Манчкин",                "Мейн-ку?н",                "Меконгский бобтейл",                "Мэнкс кошка",                "Наполеон",                "Немецкий рекс",                "Нибелунг",                "Норвежская лесная кошка",                "Ориентальная кошка",                "Оцикет",                "Персидская кошка",               "Петерболд",                "Пиксибоб",                "Рагамаффин",                "Русская голубая кошка",                "Рэгдолл",                "Саванна",                "Селкирк-рекс",                "Сиамская кошка",               "Сибирская кошка",
                "Сингапурская кошка",                "Скоттиш-фолд",                "Сноу-шу",                "Сомалийская кошка",                "Тайская кошка",                "Тойгер",                "Тонкинская кошка",                "Турецкая ангорская кошка",                "Турецкий ван",                "Украинский левкой",                "Чаузи",                "Шартрез",                "Экзотическая короткошерстная",                "Японский бобтейл"        };
        System.out.println("Идет добавление типов котов в БД...");
        for (String s : types) {
            try {
                st.execute("INSERT INTO types (type) VALUES ('" + s + "')");
            } catch (Exception e) {

            }
        }
        System.out.println("Типы кошек добавлены в БД");
    }

    public static void delete_type(int id) throws SQLException {
        //st.execute("DELETE FROM types WHERE id = x");
        PreparedStatement stmt = con.prepareStatement("DELETE FROM types WHERE id = (?)");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Строка удалена");
    }

    public static void update_type(int id, String new_type) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE types SET type = (?) WHERE id = (?)");
        stmt.setString(1, new_type);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        System.out.println("Строка обновлена");
    }

    public static String get_type(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT type FROM types WHERE id = (?)");
        stmt.setInt(1, id);
        resSet = stmt.executeQuery();
        System.out.printf("Вернули строку c типом кота: ");
        return resSet.getString("type");
    }

    public static void get_type_where(String where) throws SQLException {
        String query = "SELECT * FROM types WHERE " + where;
        System.out.println("Выполняется запрос: " + query);
        PreparedStatement stmt = con.prepareStatement(query);
        resSet = stmt.executeQuery();
        System.out.println("Вывод ответ по запросу:");
        while (resSet.next()) {
            System.out.println(resSet.getString("type"));
        }
        System.out.println("Таблица выведена");
    }

    public static int get_int_query(String x) throws SQLException {
        String query = x;
        PreparedStatement stmt = con.prepareStatement(query);
        resSet = stmt.executeQuery();
        return resSet.getInt(1);
    }

    public static void get_all_types() throws SQLException {
        String query = "SELECT type FROM types";
        PreparedStatement stmt = con.prepareStatement(query);
        resSet = stmt.executeQuery();
        System.out.println("Печатаем ответ");
        while (resSet.next()) {
            System.out.println(resSet.getString("type"));
        }
        System.out.println("Таблица выведена");
    }

    public static void insert_cat(String name, String type, int age, Double weight) throws SQLException {
        PreparedStatement stmt2 = con.prepareStatement("SELECT id FROM types WHERE type = (?)");
        stmt2.setString(1, type);
        resSet = stmt2.executeQuery();
        int type_in;
        try {
            type_in = resSet.getInt("id");
        } catch (Exception e){
            PreparedStatement stmt3 = con.prepareStatement("INSERT INTO types (type) VALUES ((?))");
            stmt3.setString(1, type);
            stmt3.execute();
            resSet = stmt2.executeQuery();
            type_in = resSet.getInt("id");
        }

        PreparedStatement stmt = con.prepareStatement("INSERT INTO cats (name, type_id, age, weight) VALUES ((?), (?), (?), (?))");
        stmt.setString(1, name);
        stmt.setInt(2, type_in);
        stmt.setInt(3, age);
        stmt.setDouble(4, weight);
        stmt.execute();
        System.out.println("Кот "+name+" добавлен в БД");
    }

    public static void add_more_cats(int n) throws SQLException {
        String[] types = new String[]{"Абиссинская кошка","Австралийский мист","Американская жесткошерстная",                "Американская короткошерстная",               "Американский бобтейл",                "Американский кёрл",               "Балинезийская кошка",                "Бенгальская кошка",                "Бирманская кошка",                "Бомбейская кошка",                "Бразильская короткошёрстная",                "Британская длинношерстная",                "Британская короткошерстная",                "Бурманская кошка",                "Бурмилла кошка",                "Гавана",                "Гималайская кошка",                "Девон-рекс",                "Донской сфинкс",                "Европейская короткошерстная",                "Египетская мау",                "Канадский сфинкс",                "Кимрик",                "Корат",                "Корниш-рекс",                "Курильский бобтейл",                "Лаперм",              "Манчкин",                "Мейн-ку?н",                "Меконгский бобтейл",                "Мэнкс кошка",                "Наполеон",                "Немецкий рекс",                "Нибелунг",                "Норвежская лесная кошка",                "Ориентальная кошка",                "Оцикет",                "Персидская кошка",               "Петерболд",                "Пиксибоб",                "Рагамаффин",                "Русская голубая кошка",                "Рэгдолл",                "Саванна",                "Селкирк-рекс",                "Сиамская кошка",               "Сибирская кошка",
                "Сингапурская кошка",                "Скоттиш-фолд",                "Сноу-шу",                "Сомалийская кошка",                "Тайская кошка",                "Тойгер",                "Тонкинская кошка",                "Турецкая ангорская кошка",                "Турецкий ван",                "Украинский левкой",                "Чаузи",                "Шартрез",                "Экзотическая короткошерстная",                "Японский бобтейл"        };
        String[] names = {"Гарфилд",                "Том",                "Гудвин",                "Рокки",                "Ленивец",
                "Пушок",                "Спорти",                "Бегемот",                "Пират",                "Гудини",                "Зорро",                "Саймон",                "Альбус",                "Базилио",                "Леопольд",                "Нарцисс",                "Атос",                "Каспер",                "Валлито",                "Оксфорд",                "Бисквит",                "Соня",                "Клеопатра",                "Цунами",                "Забияка",                "Матильда",                "Кнопка",                "Масяня",                "Царапка",                "Серсея",                "Ворсинка",                "Амели",                "Наоми",                "Маркиза",                "Изольда",                "Вальс",                "Несквик",                "Златан",                "Баскет",                "Изюм",                "Цукат",                "Мокко",                "Месси",                "Кокос",                "Адидас",
        };
        int random_type_ind;
        int random_name_ind;
        int type_in;


        for (int i =1; i <= n; i++) {
            //рандомный выбор аиндексов типов и имен кошки
            random_type_ind = (int)(Math.random() * types.length);
            random_name_ind = (int)(Math.random() * names.length);

            //рандомный выбор айди типа кошки
            PreparedStatement stmt2 = con.prepareStatement("SELECT id FROM types WHERE type = (?)");
            stmt2.setString(1, types[random_type_ind]);
            resSet = stmt2.executeQuery();
            type_in = resSet.getInt("id");

            //добавление кошки в БД с рандомными значениями
            PreparedStatement stmt = con.prepareStatement("INSERT INTO cats (name, type_id, age, weight) VALUES ((?), (?), (?), (?))");
            stmt.setString(1, names[random_name_ind]);
            stmt.setInt(2, type_in);
            stmt.setInt(3, (int)(1+Math.random()*10));
            stmt.setDouble(4, (double) (Math.random()*10));
            stmt.execute();
            System.out.println("Кот #" + i +" добавлен в БД");
        }
        System.out.println("Все коты добавлены в БД");
    }
}
