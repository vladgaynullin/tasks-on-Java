public abstract class Buildings {
    public static void main(String[] args) {
        Library library = new Library("Библиотека имени Пушкина", "Пушкинская, 333");
        library.display();
        library.repair();
        // library.tearDown(); // после снесения книги не берутся
        library.addingBooksChildrenHall(50);
        library.removeBooksChildrenHall(100);
        library.addingBooksOldHall(200);
        library.removeBooksOldHall(400);
        library.tearDown();
        library.display();

        House house = new House("Жилой дом", "Сельская, 55");
        house.buyApartment(15);
        house.display();
        house.tearDown(); // после снесения квартиры не продаются
        house.buyApartment(2);
        house.display();
    }

    private String name;            //    Название
    private String address;         //    Адрес
    private int year;               //    Год постройки
    private String architect;       //    Имя архитектора
    private boolean isCulture;      //    является ли культурным памятником

    //геттеры
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }

    public String getArchitect() {
        return architect;
    }

    public String isCulture() {
        return isCulture ? "Да" : "Нет";
    }

    //сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setArchitect(String architect) {
        this.architect = architect;
    }

    public void setCulture(boolean culture) {
        isCulture = culture;
    }


    //инициализаторы дефолтных значений
    {
        setName("Нет названия");
        setArchitect("Неизвестен");
        setAddress("Неизвестно");
        setCulture(false);
        setYear(0);
    }

    //конструкторы
    public Buildings(String name, String address, int year) {
        this.name = name;
        this.address = address;
        this.year = year;
    }

    public Buildings(String name, String address) {
        this.name = name;
        this.address = address;
    }

}

//Library, House
class Library extends Buildings implements BuildFunctions {
    private String childrenHall;          // Детский зал
    private String oldHall;               // Взрослый зал
    private double squareChildrenHall;    // Площадь детского зала
    private double squareOldHall;         // Площадь взрослого зала
    private int booksChildrenHall;        // Количество книг в детском зале
    private int booksOldHall;             // Количество книг во взрослом зале
    private int booksLibrary;             // Общее количество книг
    private boolean isOpen;               // Открыто?

    //геттеры
    public String getChildrenHall() {
        return childrenHall;
    }

    public String getOldHall() {
        return oldHall;
    }

    public double getSquareChildrenHall() {
        return squareChildrenHall;
    }

    public double getSquareOldHall() {
        return squareOldHall;
    }

    public int getBooksChildrenHall() {
        return booksChildrenHall;
    }

    public int getBooksOldHall() {
        return booksOldHall;
    }

    public int getBooksLibrary() {
        return booksChildrenHall+booksOldHall;
    }

    public String isOpen() {
        return isOpen ? "открыто" : "закрыто";
    }

    // сеттеры
    public void setChildrenHall(String childrenHall) {
        this.childrenHall = childrenHall;
    }

    public void setOldHall(String oldHall) {
        this.oldHall = oldHall;
    }

    public void setSquareChildrenHall(double squareChildrenHall) {
        this.squareChildrenHall = squareChildrenHall;
    }

    public void setSquareOldHall(double squareOldHall) {
        this.squareOldHall = squareOldHall;
    }

    public void setBooksChildrenHall(int booksChildrenHall) {
        this.booksChildrenHall = booksChildrenHall;
    }

    public void setBooksOldHall(int booksOldHall) {
        this.booksOldHall = booksOldHall;
    }

    public void setBooksLibrary() {
        this.booksLibrary = booksChildrenHall+booksOldHall;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    //инициализация дефолтных значений
    {
        setChildrenHall("Детский зал");
        setOldHall("Взрослый зал");
        setSquareChildrenHall(30);
        setSquareOldHall(70);
        setBooksChildrenHall(3000);
        setBooksOldHall(5000);
        setYear(2005);
        setBooksLibrary();
        setOpen(true);
        setCulture(true);
    }
    //конструкторы
    public Library(String name, String address) {
        super(name, address);
    }

    //методы
    void display(){
        System.out.println("Название - " + getName() + ", Адрес - " + getAddress() + ", Год постройки - " + getYear() + ", Имя архитектора - " + getArchitect()
                + ", Является культурным памятником - " + (isCulture()) + ".");
        if (isOpen) {
            System.out.println("В библиотеке есть детский зал - " + getChildrenHall() + " , площадью - " + getSquareChildrenHall() + " кв.м., взрослый зал - " + getOldHall() + ", площадью - " + getSquareOldHall() + "кв.м.");
            System.out.println("Общее количество книг в библиотеке - " + getBooksLibrary() + " , из них в детском зале - " + getBooksChildrenHall() + " и " + getBooksOldHall() + " во взрослом зале.");
        }
        System.out.println("Сейчас у нас " + isOpen() + "\n");
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void repair() {
        setYear(2022);
    }

    @Override
    public void tearDown() {
        setName(getName()+" (снесена)");
        setOpen(false);
    }

    void addingBooksChildrenHall(int n){
        if (isOpen) booksChildrenHall += n;
    }

    void removeBooksChildrenHall(int n) {
        if (isOpen) booksChildrenHall -= n;
    }

    void addingBooksOldHall(int n){
        if (isOpen) booksOldHall += n;
    }

    void removeBooksOldHall(int n) {
        if (isOpen) booksOldHall -= n;
    }
}

class House extends Buildings implements BuildFunctions {

    private int numberFloors;                // Количество этажей
    private int numberFrontDoor;             // Количество подъездов в доме
    private int numberApartmentsEntrance;    // Квартир в подъезде
    private int numberApartmentsFloor;       // Квартир на этаже
    private int numberApartmentsHouse;       // Квартир в доме
    private int freeApartment;               // Свободных квартир
    private boolean isExists;               // Существует?
    {
        setYear(2001);
        setArchitect("ГосСтрой");
        setCulture(false);
        setNumberFloors(9); // Количество этажей
        setNumberFrontDoor(5); // Количество подъездов в доме
        setNumberApartmentsEntrance(36); // Квартир в подъезде
        setNumberApartmentsFloor(4); // Квартир на этаже
        setNumberApartmentsHouse(numberApartmentsHouse);
        setFreeApartment(30);
        setExists(true);
    }
    //геттеры
    public int getNumberFloors() {
        return numberFloors;
    }

    public int getNumberFrontDoor() {
        return numberFrontDoor;
    }

    public int getNumberApartmentsEntrance() {
        return numberApartmentsEntrance;
    }

    public int getNumberApartmentsFloor() {
        return numberApartmentsFloor;
    }

    public int getNumberApartmentsHouse() {
        return numberApartmentsHouse;
    }

    public int getFreeApartment() {
        return freeApartment;
    }
    // сеттеры
    public void setNumberFloors(int numberFloors) {
        this.numberFloors = numberFloors;
    }

    public void setNumberFrontDoor(int numberFrontDoor) {
        this.numberFrontDoor = numberFrontDoor;
    }

    public void setNumberApartmentsEntrance(int numberApartmentsEntrance) {
        this.numberApartmentsEntrance = numberApartmentsEntrance;
    }

    public void setNumberApartmentsFloor(int numberApartmentsFloor) {
        this.numberApartmentsFloor = numberApartmentsFloor;
    }

    public void setNumberApartmentsHouse(int numberApartmentsHouse) {
        this.numberApartmentsHouse = numberApartmentsEntrance*numberFrontDoor;
    }

    public void setFreeApartment(int freeApartment) {
        this.freeApartment = freeApartment;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    //конструкторы
    public House(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return Buildings.class.getSimpleName();
    }

    @Override
    public void repair() {
        setYear(2022);
    }

    @Override
    public void tearDown() {
        setName(getName()+" (снесен)");
        setExists(false);
        freeApartment = 0;
    }

    public void buyApartment(int n) {
        if (n <= freeApartment) {
            System.out.println("Вы приобрели " + n + " квартир");
            freeApartment -= n;
        } else {
            System.out.println("Покупка неудалась! Доступно только " + freeApartment + " квартир");
        }
    }
    public void display(){
        System.out.println("Название - " + getName() + ", Адрес - " + getAddress() + ", Год постройки - " + getYear() + ", Имя архитектора - " + getArchitect()
                + ", Является культурным памятником - " + (isCulture()) + ".");
        if (isExists){
        System.out.println("В доме - " + getNumberFloors() + " этажей, на каждом из которых - " + getNumberApartmentsFloor() + " квартиры; " + getNumberFrontDoor() +
                " подъездов, в каждом из которых - " + getNumberApartmentsEntrance() + " квартир");
        System.out.println("Всего квартир в доме - " + getNumberApartmentsHouse() + ", из них для покупки доступны - " + getFreeApartment() + "\n");
    }
    }
}
