import java.sql.SQLException;

public class db {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        myCats.Conn();
        //создаем таблицу "Типы котов"
        myCats.CreateDB();
        //создаем таблицу "Коты"
        myCats.CreateDB_2();
        //заполняем таблицу "Типы котов"
        myCats.InsertDB();
        //заполняем таблицу "Коты" рандомными котами в количестве n
        myCats.add_more_cats(10);
        // добавляем кота в таблицу "Коты"
        myCats.insert_cat("Барсик", "Гавана", 5, 7.0);
        // добавляем кота в таблицу "Коты" с несуществующим типом (тип добавляется в таблицу "Типы")
        myCats.insert_cat("Вася", "Гав-гав", 6, 8.0);
        // удаляем тип из таблицы
        myCats.delete_type(15);
        // переписываем тип в таблице "Типы"
        myCats.update_type(16, "Дворовый кот");
        // выводим тип кота по индексу
        System.out.println(myCats.get_type(16));
        // выводим котов с условиями where
        myCats.get_type_where("type LIKE '%кая%'");
        // выводим все типы котов
        //myCats.get_all_types();
        myCats.CloseDB();
    }
}