import java.sql.SQLException;

public class db {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        myCats.Conn();
        //������� ������� "���� �����"
        myCats.CreateDB();
        //������� ������� "����"
        myCats.CreateDB_2();
        //��������� ������� "���� �����"
        myCats.InsertDB();
        //��������� ������� "����" ���������� ������ � ���������� n
        myCats.add_more_cats(10);
        // ��������� ���� � ������� "����"
        myCats.insert_cat("������", "������", 5, 7.0);
        // ��������� ���� � ������� "����" � �������������� ����� (��� ����������� � ������� "����")
        myCats.insert_cat("����", "���-���", 6, 8.0);
        // ������� ��� �� �������
        myCats.delete_type(15);
        // ������������ ��� � ������� "����"
        myCats.update_type(16, "�������� ���");
        // ������� ��� ���� �� �������
        System.out.println(myCats.get_type(16));
        // ������� ����� � ��������� where
        myCats.get_type_where("type LIKE '%���%'");
        // ������� ��� ���� �����
        //myCats.get_all_types();
        myCats.CloseDB();
    }
}