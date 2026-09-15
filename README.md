# System zarządzania magazynem stacji benzynowej

## Opis projektu

Aplikacja webowa wspierająca codzienną pracę stacji benzynowej w zakresie zarządzania dostawami i stanem magazynowym produktów spożywczych (parówki, burgery, kanapki, napoje, pieczywo, nabiał i inne).

System pozwala na:

- **Zarządzanie produktami** — katalog produktów z kategoriami, jednostkami miary i kodami kreskowymi (odczyt z obrazu)
- **Zarządzanie partiami produktów** — każda dostawa danego produktu jest osobną partią z własnym terminem ważności, dzięki czemu ten sam produkt może mieć jednocześnie kilka różnych dat przydatności do spożycia
- **Składanie i przyjmowanie zamówień** — tworzenie zamówień u dostawców, a następnie porównywanie tego co zamówiono z tym co faktycznie dostarczono
- **Zarządzanie dostawami** — historia dostaw wraz z informacją kto ją przyjął, czy jest opłacona i czy dotarła nienaruszona
- **Zarządzanie dostawcami** — kartoteka dostawców z danymi NIP, REGON i adresem
- **Zarządzanie pracownikami** — konta pracowników z rozróżnieniem uprawnień administratora i zwykłego pracownika
- **Logowanie i autoryzacja** — dostęp do systemu wymaga zalogowania; zakres widocznych funkcji zależy od roli pracownika
- **Wyszukiwanie** — proste wyszukiwarki tekstowe dla produktów, dostawców, pracowników i dostaw
- **Soft delete** — usunięte rekordy nie znikają fizycznie z bazy, tylko są oznaczane jako usunięte, co zachowuje historię

### Role użytkowników

| Rola | Uprawnienia |
|---|---|
| **Administrator** | Pełny dostęp: zarządzanie pracownikami, dostawcami, produktami, dostawami i zamówieniami |
| **Pracownik** | Podgląd produktów, składanie i sprawdzanie zamówień, przyjmowanie dostaw |

---

## Technologie

- **Java 17**
- **Spring Boot 3.x** (Spring MVC, Spring Data JPA, Spring Security)
- **MySQL** — baza danych
- **JSP + JSTL** — warstwa widoku
- **Hibernate** — mapowanie ORM
- **BCrypt (jBCrypt)** — hashowanie haseł
- **Hibernate Validator (Bean Validation)** — walidacja danych wejściowych
- **ZXing** — odczyt kodów kreskowych ze zdjęć
- **Bootstrap (SB Admin 2)** — warstwa graficzna

---

## Wymagania wstępne

Przed instalacją upewnij się, że masz zainstalowane:

- **JDK 17** lub nowszy
- **Maven 3.8+**
- **MySQL 8.0+**
- **IntelliJ IDEA** (lub inne IDE wspierające Spring Boot)

---

## Instalacja i konfiguracja

### 1. Sklonuj repozytorium

```bash
git clone https://github.com/TWOJA_NAZWA/xyz.git
cd xyz
```

### 2. Stwórz bazę danych

W MySQL Workbench lub terminalu:

```sql
CREATE DATABASE xyz;
```

### 3. Skonfiguruj połączenie z bazą danych

Otwórz `src/main/resources/application.properties` i uzupełnij dane dostępowe:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/xyz?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TWOJE_HASLO
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

> **Uwaga:** plik `application.properties` zawiera dane dostępowe do bazy i nie powinien być publikowany w repozytorium. Upewnij się, że znajduje się w `.gitignore`.

### 4. Zbuduj projekt

```bash
mvn clean install
```

### 5. Uruchom aplikację

Z poziomu IntelliJ: uruchom klasę główną `*Application.java`.

Z terminala:

```bash
mvn spring-boot:run
```

Aplikacja domyślnie wystartuje pod adresem:

```
http://localhost:8080
```

### 6. Pierwsze logowanie

Przy pierwszym uruchomieniu baza danych jest pusta — nie ma jeszcze żadnego konta pracownika. Aby się zalogować, trzeba tymczasowo wyłączyć zabezpieczenia logowania, dodać pierwszego administratora, a następnie przywrócić logowanie.

1. W klasie `SecurityConfig` zakomentuj adnotację `@EnableWebSecurity`
2. Uruchom aplikację i wejdź na `/employee/add`, dodaj pracownika z zaznaczonymi uprawnieniami administratora
3. Odkomentuj `@EnableWebSecurity` z powrotem
4. Zaloguj się utworzonym kontem na stronie `/login`

---

## Przewodnik użytkownika

### Logowanie

Po uruchomieniu aplikacji przejdź na `http://localhost:8080/login` i zaloguj się loginem i hasłem swojego konta pracownika.

### Zarządzanie produktami

- **Lista produktów** (`/product/list`) — pokazuje wszystkie produkty wraz z łączną ilością dostępną w magazynie (sumowaną ze wszystkich aktywnych partii) oraz umożliwia filtrowanie po kategorii
- **Dodawanie produktu** (`/product/add`) — utworzenie nowej pozycji katalogowej (nazwa, kod kreskowy, kategoria, jednostka miary); kod kreskowy można też zeskanować ze zdjęcia
- **Szczegóły produktu** (`/product/details`) — pokazuje wszystkie partie danego produktu wraz z terminami ważności; wiersze z terminem upływającym w ciągu 7 dni są oznaczone na żółto, a przeterminowane lub kończące się dziś na czerwono
- **Dodawanie partii** (`/product/addbatch`) — dodanie nowej dostawy istniejącego produktu wraz z ilością i terminem ważności

### Dostawcy

- **Lista dostawców** (`/supplier/list`) — dostęp tylko dla administratora
- **Dodawanie dostawcy** — wymaga podania nazwy, unikalnego NIP i REGON oraz adresu

### Zamówienia

1. **Nowe zamówienie** (`/productorder/add`) — wybierz dostawcę i dodaj listę produktów wraz z zamawianymi ilościami. Zamówieniu automatycznie nadawany jest unikalny numer.
2. **Lista zamówień** (`/productorder/list`) — pokazuje status każdego zamówienia (oczekuje / dostarczono)
3. **Szczegóły zamówienia** (`/productorder/show`) — po dotarciu dostawy, dla każdej zamówionej pozycji wpisz faktycznie otrzymaną ilość i termin ważności. System automatycznie porówna zamówioną i otrzymaną ilość i oznaczy pozycję jako zgodną lub z rozbieżnością.

### Dostawy

- **Nowa dostawa** (`/delivery/add`) — powiązanie fizycznie dostarczonej przesyłki z wcześniej złożonym zamówieniem; system automatycznie przypisuje pracownika przyjmującego (zalogowanego użytkownika) i numer dostawy zgodny z numerem zamówienia
- **Lista dostaw** (`/delivery/list`) — historia wszystkich dostaw z możliwością wyszukiwania

### Pracownicy (tylko administrator)

- **Lista pracowników** (`/employee/list`)
- **Dodawanie pracownika** (`/employee/add`) — hasło jest automatycznie hashowane przed zapisem
- **Edycja pracownika** — pole hasła można zostawić puste, jeśli nie chcesz go zmieniać

### Wyszukiwanie

Każda lista (produkty, dostawcy, pracownicy, dostawy, zamówienia) posiada pole wyszukiwania tekstowego, które przeszukuje kilka pól jednocześnie (np. nazwę, numer, kategorię).

---

## Struktura projektu

```
src/main/java/.../
├── config/          — konfiguracja Spring Security
├── product/         — encje i logika produktów oraz partii produktów
├── supplier/        — encje i logika dostawców
├── employee/         — encje i logika pracowników, logowanie
├── delivery/         — encje i logika dostaw
├── orderedProducts/  — encje i logika zamówień oraz pozycji zamówień
└── barcode/          — serwis odczytu kodów kreskowych

src/main/webapp/WEB-INF/views/
├── product/
├── supplier/
├── employee/
├── delivery/
├── ordered/
└── header.jsp, footer.jsp, login.jsp
```

---

## Znane ograniczenia

- Aplikacja nie posiada mechanizmu odzyskiwania hasła
- Rejestracja nowych pracowników możliwa jest wyłącznie przez administratora, nie ma samodzielnej rejestracji

---
