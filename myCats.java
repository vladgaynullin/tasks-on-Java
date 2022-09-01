import java.sql.*;

public class myCats {
    public static Connection con;
    public static Statement st;
    public static ResultSet resSet;
    public static void Conn() throws ClassNotFoundException, SQLException {
        //con = null;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
        System.out.println("���� �������");
    }
    public static void CreateDB() throws SQLException {
        st = con.createStatement();
        st.execute("CREATE TABLE if not exists types (id INTEGER PRIMARY KEY AUTOINCREMENT, type VARCHAR(100) UNIQUE)");
        System.out.println("������� ���� �������");
    }

    public static void CreateDB_2() throws SQLException {
        st = con.createStatement();
        st.execute("CREATE TABLE if not exists cats (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20) NOT NULL, type_id INTEGER NOT NULL, age INTEGER NOT NULL, weight DOUBLE," +
                "FOREIGN KEY(type_id) REFERENCES types (id))");
        System.out.println("������� ���� �������");
    }

    public static void CloseDB() throws SQLException {
        con.close();
        st.close();
    }

    //��������� ������� ���� �����
    public static void InsertDB() throws SQLException {
        //st.execute("DELETE FROM types");
        String[] types = new String[]{"����������� �����","������������� ����","������������ ��������������",                "������������ ���������������",               "������������ �������",                "������������ ���",               "������������� �����",                "����������� �����",                "���������� �����",                "���������� �����",                "����������� ���������������",                "���������� ��������������",                "���������� ���������������",                "���������� �����",                "�������� �����",                "������",                "����������� �����",                "�����-����",                "������� ������",                "����������� ���������������",                "���������� ���",                "��������� ������",                "������",                "�����",                "������-����",                "���������� �������",                "������",              "�������",                "����-��?�",                "���������� �������",                "����� �����",                "��������",                "�������� ����",                "��������",                "���������� ������ �����",                "������������ �����",                "������",                "���������� �����",               "���������",                "��������",                "����������",                "������� ������� �����",                "�������",                "�������",                "�������-����",                "�������� �����",               "��������� �����",
                "������������ �����",                "�������-����",                "����-��",                "����������� �����",                "������� �����",                "������",                "���������� �����",                "�������� ��������� �����",                "�������� ���",                "���������� ������",                "�����",                "�������",                "������������ ���������������",                "�������� �������"        };
        System.out.println("���� ���������� ����� ����� � ��...");
        for (String s : types) {
            try {
                st.execute("INSERT INTO types (type) VALUES ('" + s + "')");
            } catch (Exception e) {

            }
        }
        System.out.println("���� ����� ��������� � ��");
    }

    public static void delete_type(int id) throws SQLException {
        //st.execute("DELETE FROM types WHERE id = x");
        PreparedStatement stmt = con.prepareStatement("DELETE FROM types WHERE id = (?)");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("������ �������");
    }

    public static void update_type(int id, String new_type) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("UPDATE types SET type = (?) WHERE id = (?)");
        stmt.setString(1, new_type);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        System.out.println("������ ���������");
    }

    public static String get_type(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT type FROM types WHERE id = (?)");
        stmt.setInt(1, id);
        resSet = stmt.executeQuery();
        System.out.printf("������� ������ c ����� ����: ");
        return resSet.getString("type");
    }

    public static void get_type_where(String where) throws SQLException {
        String query = "SELECT * FROM types WHERE " + where;
        System.out.println("����������� ������: " + query);
        PreparedStatement stmt = con.prepareStatement(query);
        resSet = stmt.executeQuery();
        System.out.println("����� ����� �� �������:");
        while (resSet.next()) {
            System.out.println(resSet.getString("type"));
        }
        System.out.println("������� ��������");
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
        System.out.println("�������� �����");
        while (resSet.next()) {
            System.out.println(resSet.getString("type"));
        }
        System.out.println("������� ��������");
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
        System.out.println("��� "+name+" �������� � ��");
    }

    public static void add_more_cats(int n) throws SQLException {
        String[] types = new String[]{"����������� �����","������������� ����","������������ ��������������",                "������������ ���������������",               "������������ �������",                "������������ ���",               "������������� �����",                "����������� �����",                "���������� �����",                "���������� �����",                "����������� ���������������",                "���������� ��������������",                "���������� ���������������",                "���������� �����",                "�������� �����",                "������",                "����������� �����",                "�����-����",                "������� ������",                "����������� ���������������",                "���������� ���",                "��������� ������",                "������",                "�����",                "������-����",                "���������� �������",                "������",              "�������",                "����-��?�",                "���������� �������",                "����� �����",                "��������",                "�������� ����",                "��������",                "���������� ������ �����",                "������������ �����",                "������",                "���������� �����",               "���������",                "��������",                "����������",                "������� ������� �����",                "�������",                "�������",                "�������-����",                "�������� �����",               "��������� �����",
                "������������ �����",                "�������-����",                "����-��",                "����������� �����",                "������� �����",                "������",                "���������� �����",                "�������� ��������� �����",                "�������� ���",                "���������� ������",                "�����",                "�������",                "������������ ���������������",                "�������� �������"        };
        String[] names = {"�������",                "���",                "������",                "�����",                "�������",
                "�����",                "������",                "�������",                "�����",                "������",                "�����",                "������",                "������",                "�������",                "��������",                "�������",                "����",                "������",                "�������",                "�������",                "�������",                "����",                "���������",                "������",                "�������",                "��������",                "������",                "������",                "�������",                "������",                "��������",                "�����",                "�����",                "�������",                "�������",                "�����",                "�������",                "������",                "������",                "����",                "�����",                "�����",                "�����",                "�����",                "������",
        };
        int random_type_ind;
        int random_name_ind;
        int type_in;


        for (int i =1; i <= n; i++) {
            //��������� ����� ��������� ����� � ���� �����
            random_type_ind = (int)(Math.random() * types.length);
            random_name_ind = (int)(Math.random() * names.length);

            //��������� ����� ���� ���� �����
            PreparedStatement stmt2 = con.prepareStatement("SELECT id FROM types WHERE type = (?)");
            stmt2.setString(1, types[random_type_ind]);
            resSet = stmt2.executeQuery();
            type_in = resSet.getInt("id");

            //���������� ����� � �� � ���������� ����������
            PreparedStatement stmt = con.prepareStatement("INSERT INTO cats (name, type_id, age, weight) VALUES ((?), (?), (?), (?))");
            stmt.setString(1, names[random_name_ind]);
            stmt.setInt(2, type_in);
            stmt.setInt(3, (int)(1+Math.random()*10));
            stmt.setDouble(4, (double) (Math.random()*10));
            stmt.execute();
            System.out.println("��� #" + i +" �������� � ��");
        }
        System.out.println("��� ���� ��������� � ��");
    }
}
