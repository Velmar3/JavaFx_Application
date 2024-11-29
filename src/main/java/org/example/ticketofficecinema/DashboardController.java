package org.example.ticketofficecinema;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;


public class DashboardController implements Initializable {

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_date;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_duration;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> addMovies_col_movieTitle;

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<moviesData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_genre;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<moviesData, String> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<Integer> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_receiptBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Button manual_btn;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<Integer> availableMovies_specialClass_quantity;

    @FXML
    private TableView<moviesData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<customerData, String> customers_col_date;

    @FXML
    private TableColumn<customerData, String> customers_col_genre;

    @FXML
    private TableColumn<customerData, String> customers_col_movieTitle;

    @FXML
    private TableColumn<customerData, String> customers_col_ticketNumber;

    @FXML
    private TableColumn<customerData, String> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<customerData> customers_tableView;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private AnchorPane dashboard_for;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalEarnToday;

    @FXML
    private Label dashboard_totalSoldTicket;

    @FXML
    private Button dashbord_btn;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_current;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_duration;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_genre;

    @FXML
    private TableColumn<moviesData, String> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<moviesData> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private Label username;

    @FXML
    private AnchorPane topForm;

    @FXML
    private AnchorPane dashboard_slide_form;

    @FXML
    private ImageView imageSlide;



    private Image image;

    private double x = 0;
    private double y = 0;

    // Tools for database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;

    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;

    public int countSlide;

    public void slideShow() {

        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\java\\org\\example\\ticketofficecinema\\image\\slides\\gemeimet.jpg"));
        images.add(new Image("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\java\\org\\example\\ticketofficecinema\\image\\slides\\dzhoker.jpg"));
        images.add(new Image("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\java\\org\\example\\ticketofficecinema\\image\\slides\\led.jpg"));
        images.add(new Image("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\java\\org\\example\\ticketofficecinema\\image\\slides\\marvel.jpg"));
        images.add(new Image("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\java\\org\\example\\ticketofficecinema\\image\\slides\\maxresdefault.jpg"));

        imageSlide.setImage(images.get(0));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {

            imageSlide.setImage(images.get(countSlide));

            countSlide++;

            if (countSlide == 5) {
                countSlide = 0;
            }

        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private int totalMovies;

    public void totalAvailableMovies() {

        String sql = "SELECT COUNT(id) FROM movie WHERE current = 'Показ'";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalMovies = result.getInt("count(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalAvailableMovies() {
        totalAvailableMovies();
        dashboard_availableMovies.setText(String.valueOf(totalMovies));
    }

    private double totalIncome;

    public void totalIncomeToday() {

        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(total) FROM customer WHERE date = '"
                + String.valueOf(sqlDate) + "'";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalIncome = result.getDouble("SUM(total)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotalIncomeToday() {
        totalIncomeToday();
        dashboard_totalEarnToday.setText(String.valueOf(totalIncome));
    }

    private int soldTicket;

    public void countTicket() {

        String sql = "SELECT count(id) FROM customer";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                soldTicket = result.getInt("count(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void displayTotalSoldTicket() {
        countTicket();
        dashboard_totalSoldTicket.setText(String.valueOf(soldTicket));
    }

    public void searchCustomer() {

        FilteredList<customerData> filter = new FilteredList<>(custList, e -> true);

        customers_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateCustomer -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String keySearch = newValue.toLowerCase();

                if (predicateCustomer.getId().toString().contains(keySearch)) {
                    return true;
                } else if (predicateCustomer.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateCustomer.getTime().toString().contains(keySearch)) {
                    return true;
                } else if (predicateCustomer.getDate().toString().contains(keySearch)) {
                    return true;
                }

                return false;

            });
        });

        SortedList<customerData> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(customers_tableView.comparatorProperty());

        customers_tableView.setItems(sort);

    }

    public ObservableList<customerData> customerList() {

        ObservableList<customerData> customerL = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer";

        connect = DataBase.connectDb();

        try {

            customerData customerD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                customerD = new customerData(result.getInt("id"),
                        result.getString("type"),
                        result.getString("movieTitle"),
                        result.getInt("quantity"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getTime("time"));

                customerL.add(customerD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerL;
    }

    private ObservableList<customerData> custList;

    public void showCustomerList() {

        custList = customerList();

        customers_col_ticketNumber.setCellValueFactory(new PropertyValueFactory<>("id"));
        customers_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        customers_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        customers_tableView.setItems(custList);
    }

    public void selectCustomerList() {

        customerData custD = customers_tableView.getSelectionModel().getSelectedItem();
        int num = customers_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        customers_ticketNumber.setText(String.valueOf(custD.getId()));
        customers_movieTitle.setText(custD.getTitle());
        customers_date.setText(String.valueOf(custD.getDate()));
        customers_time.setText(String.valueOf(custD.getTime()));

    }

    public void deleteCustomer() {

        String sql = "DELETE FROM customer WHERE id = '" + customers_ticketNumber.getText() + "'";

        connect = DataBase.connectDb();

        try {

            Alert alert;

            statement = connect.createStatement();


            if (customers_ticketNumber.getText().isEmpty()
                    || customers_movieTitle.getText().isEmpty()
                    || customers_date.getText().isEmpty()
                    || customers_time.getText().isEmpty()) {


                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите билет!");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Готовы удалить билет на фильм " + customers_movieTitle.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {

                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Сообщение");
                    alert.setHeaderText(null);
                    alert.setContentText("Билет " + addMovies_movieTitle.getText() + " удален!");
                    alert.showAndWait();

                    showCustomerList();
                    clearCustomer();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearCustomer() {

        customers_ticketNumber.setText("");
        customers_movieTitle.setText("");
        customers_date.setText("");
        customers_time.setText("");

    }

    public void receipt() {

        if (total > 0) {

            HashMap hash = new HashMap();
            hash.put("receipt", num);

            try {

                JasperDesign jDesign = JRXmlLoader.load("src/main/resources/org/example/ticketofficecinema/reports/report.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, connect);

                JasperViewer.viewReport(jPrint, false);

            } catch (JRException e) {
                //e.printStackTrace();
            }
        } else {

            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Сообщение об ошибке");
            alert.setHeaderText(null);
            alert.setContentText("Выберите необходимое количество мест!");
            alert.showAndWait();

        }
    }

    private int num;
    private int qty;

    public void buy() {

        String sql = "INSERT INTO customer (type, movieTitle, quantity, total, date, time) VALUES(?,?,?,?,?,?)";

        connect = DataBase.connectDb();

        String type = "";

        if (price2 > 0 && price1 == 0) {
            type = "Премиум";
        } else if (price1 > 0 && price2 == 0) {
            type = "Обычные";
        } else if (price1 > 0 && price2 > 0) {
            type = "Обычные и Премиум";
        }


        java.util.Date date = new java.util.Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {

            LocalTime localTime = LocalTime.now();
            Time time = Time.valueOf(localTime);

            qty = qty1 + qty2;

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, type);
            prepare.setString(2, availableMovies_title.getText());
            prepare.setString(3, String.valueOf(qty));
            prepare.setString(4, String.valueOf(total));
            prepare.setString(5, String.valueOf(setDate));
            prepare.setString(6, String.valueOf(time));

            Alert alert;

            if (availableMovies_imageView.getImage() == null
                    || availableMovies_title.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите фильм!");
                alert.showAndWait();

            } else if (price1 == 0 && price2 == 0) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите необходимое количество мест!");
                alert.showAndWait();

            } else {

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Билеты успешно куплены!");
                alert.showAndWait();

                String sql1 = "SELECT * FROM customer";

                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();

                num = 0;

                while (result.next()) {
                    num = result.getInt("id");
                }

                String sql2 = "INSERT INTO customer_info (customer_id, type, quantity, total, movieTitle) VALUES (?,?,?,?,?)";

                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, String.valueOf(num));
                prepare.setString(2, type);
                prepare.setString(3, String.valueOf(qty));
                prepare.setString(4, String.valueOf(total));
                prepare.setString(5, availableMovies_title.getText());
                prepare.execute();

                clearPurchaseTicketInfo();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearPurchaseTicketInfo() {

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);

        availableMovies_normalClass_quantity.setValueFactory(spinner1);
        availableMovies_specialClass_quantity.setValueFactory(spinner2);

        availableMovies_normalClass_price.setText("0.0 BYN");
        availableMovies_specialClass_price.setText("0.0 BYN");
        availableMovies_total.setText("0.0 BYN");

        availableMovies_imageView.setImage(null);
        availableMovies_title.setText("");

    }

    public void showSpinnerValue() {

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);

        availableMovies_normalClass_quantity.setValueFactory(spinner1);
        availableMovies_specialClass_quantity.setValueFactory(spinner2);

    }

    public void getSpinnerValue(MouseEvent event) {

        qty1 = availableMovies_normalClass_quantity.getValue();
        qty2 = availableMovies_specialClass_quantity.getValue();

        price1 = (qty1 * 15);
        price2 = (qty2 * 40);

        total = price1 + price2;

        availableMovies_normalClass_price.setText(String.valueOf(price1) + " BYN");
        availableMovies_specialClass_price.setText(String.valueOf(price2) + " BYN");

        availableMovies_total.setText(String.valueOf(total) + " BYN");

    }

    public ObservableList<moviesData> availableMoviesList() {

        ObservableList<moviesData> listAvMovies = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie WHERE current = 'Показ'";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movData;

            while (result.next()) {

                movData = new moviesData(result.getInt("id")
                        , result.getString("movieTitle")
                        , result.getString("genre")
                        , result.getString("duration")
                        , result.getString("image")
                        , result.getDate("date")
                        , result.getString("current"));

                listAvMovies.add(movData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAvMovies;
    }

    private ObservableList<moviesData> availableMoviesList;

    public void showAvailableMovies() {

        availableMoviesList = availableMoviesList();

        availableMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableMovies_tableView.setItems(availableMoviesList);

    }

    public void selectAvailableMovies() {

        moviesData movData = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableMovies_movieTitle.setText(movData.getTitle());
        availableMovies_genre.setText(movData.getGenre());
        availableMovies_date.setText(String.valueOf(movData.getDate()));

        getData.path = movData.getImage();
        getData.title = movData.getTitle();
    }

    public void selectMovie() {

        Alert alert;

        if (availableMovies_movieTitle.getText().isEmpty()
                || availableMovies_genre.getText().isEmpty()
                || availableMovies_date.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Сообщение об ошибке");
            alert.setHeaderText(null);
            alert.setContentText("Выберите фильм!");
            alert.showAndWait();

        } else {

            String uri = "file:" + getData.path;
            image = new Image(uri, 146, 172, false, true);
            availableMovies_imageView.setImage(image);

            availableMovies_title.setText(getData.title);

            availableMovies_movieTitle.setText("");
            availableMovies_genre.setText("");
            availableMovies_date.setText("");

        }
    }

    private String[] currentList = {"Показ", "Не показ"};

    public void comboBox() {

        List<String> listCurrent = new ArrayList<>();

        for (String data : currentList) {
            listCurrent.add(data);
        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);

    }

    public void updateEditScreening() {

        String sql = "UPDATE movie SET current = '"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movieTitle = '" + editScreening_title.getText() + "'";

        connect = DataBase.connectDb();

        try {

            statement = connect.createStatement();

            Alert alert;

            if (editScreening_title.getText().isEmpty()
                    || editScreening_imageView.getImage() == null
                    || editScreening_current.getSelectionModel().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Выберите фильм!");
                alert.showAndWait();

            } else {

                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Фильм " + addMovies_movieTitle.getText() + " обновлен!");
                alert.showAndWait();

                showEditScreening();
                clearEditScreening();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearEditScreening() {

        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
        //editScreening_current.setSelectionModel(null);

    }

    public void searchEditScreening() {

        FilteredList<moviesData> filter = new FilteredList<>(editScreeningL, e -> true);

        editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMoviesData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String keySearch = newValue.toLowerCase();

                if (predicateMoviesData.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getCurrent().toLowerCase().contains(keySearch)) {
                    return true;
                }

                return false;

            });
        });

        SortedList<moviesData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());

        editScreening_tableView.setItems(sortData);


    }

    public void selectEditScreening() {

        moviesData movData = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        String uri = "file:" + movData.getImage();
        image = new Image(uri, 146, 172, false, true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(movData.getTitle());
    }

    public ObservableList<moviesData> editScreeningList() {

        ObservableList<moviesData> editSList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movData;

            while (result.next()) {

                movData = new moviesData(result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));

                editSList.add(movData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return editSList;
    }

    private ObservableList<moviesData> editScreeningL;

    public void showEditScreening() {

        editScreeningL = editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);

    }

    public void searchAddMovies() {

        FilteredList<moviesData> filter = new FilteredList<>(listAddMovies, e -> true);

        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMoviesData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String keySearch = newValue.toLowerCase();

                if (predicateMoviesData.getTitle().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDuration().toLowerCase().contains(keySearch)) {
                    return true;
                } else if (predicateMoviesData.getDate().toString().contains(keySearch)) {
                    return true;
                }

                return false;

            });
        });

        SortedList<moviesData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());

        addMovies_tableView.setItems(sortData);

    }

    public void importImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Выберите файл");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString(), 148, 176, false, true);
            addMovies_imageView.setImage(image);

            getData.path = file.getAbsolutePath();
        }
    }

    public void movieId() {

        String sql = "SELECT count(id) FROM movie";
        connect = DataBase.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getData.movieId = result.getInt("count(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertAddMovies() {

        String sql1 = "SELECT * FROM movie WHERE movieTitle = '" + addMovies_movieTitle.getText() + "'";

        connect = DataBase.connectDb();

        Alert alert;

        try {

            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if (result.next()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + "Фильм имеется в списке!");
                alert.showAndWait();

            } else {
                if (addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Сообщение об ошибке");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните поля, верными значениями!");
                    alert.showAndWait();
                } else {

                    String sql = "INSERT INTO movie (id, movieTitle, genre, duration, image, date, current) VALUES (?,?,?,?,?,?,?)";

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    movieId();

                    String movId = String.valueOf(getData.movieId + 1);

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, movId);
                    prepare.setString(2, addMovies_movieTitle.getText());
                    prepare.setString(3, addMovies_genre.getText());
                    prepare.setString(4, addMovies_duration.getText());
                    prepare.setString(5, uri);
                    prepare.setString(6, String.valueOf(addMovies_date.getValue()));
                    prepare.setString(7, "Показ");

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Сообщение");
                    alert.setHeaderText(null);
                    alert.setContentText("Фильм " + addMovies_movieTitle.getText() + " добавлен!");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAddMovies() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE movie SET movieTitle = '" + addMovies_movieTitle.getText()
                + "', genre = '" + addMovies_genre.getText()
                + "', duration = '" + addMovies_duration.getText()
                + "', image = '" + uri
                + "', date = '" + addMovies_date.getValue()
                + "' WHERE id = '" + String.valueOf(getData.movieId) + "'";

        connect = DataBase.connectDb();

        try {

            statement = connect.createStatement();

            Alert alert;

            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Необходимо выбрать фильм!");
                alert.showAndWait();

            } else {

                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Фильм " + addMovies_movieTitle.getText() + " изменен!");
                alert.showAndWait();

                showAddMoviesList();
                clearAddMoviesList();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAddMovies() {

        String sql = "DELETE FROM movie WHERE movieTitle = '"
                + addMovies_movieTitle.getText() + "'";

        connect = DataBase.connectDb();

        try {

            statement = connect.createStatement();

            Alert alert;

            if (addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Необходимо выбрать фильм!");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Сообщение");
                alert.setHeaderText(null);
                alert.setContentText("Готовы удалить фильм " + addMovies_movieTitle.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (ButtonType.OK.equals(option.get())) {

                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Сообщение");
                    alert.setHeaderText(null);
                    alert.setContentText("Фильм " + addMovies_movieTitle.getText() + " удален!");
                    alert.showAndWait();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddMoviesList() {

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);

    }

    public ObservableList<moviesData> addMoviesList() {

        ObservableList<moviesData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";
        connect = DataBase.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            moviesData movData;

            while (result.next()) {
                movData = new moviesData(result.getInt("id"),
                        result.getString("movieTitle"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));

                listData.add(movData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<moviesData> listAddMovies;

    public void showAddMoviesList() {

        listAddMovies = addMoviesList();
        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);

    }

    public void selectAddMoviesList() {

        moviesData movData = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getData.path = movData.getImage();
        getData.movieId = movData.getId();

        addMovies_movieTitle.setText(movData.getTitle());
        addMovies_genre.setText(movData.getGenre());
        addMovies_duration.setText(movData.getDuration());

        String getDate = String.valueOf(movData.getDate());

        String uri = "file:" + movData.getImage();

        image = new Image(uri, 148, 176, false, true);
        addMovies_imageView.setImage(image);

        addMovies_date.setValue(LocalDate.parse(getDate));
    }

    public void logout() {

        signout.getScene().getWindow().hide();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/ticketofficecinema/TicketOfficeCinema.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashbord_btn) {

            dashboard_form.setVisible(true);
            dashboard_slide_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashbord_btn.setStyle("-fx-background-color: #ae2d3c");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            displayTotalSoldTicket();
            displayTotalIncomeToday();
            displayTotalAvailableMovies();

        } else if (event.getSource() == addMovies_btn) {

            dashboard_form.setVisible(false);
            dashboard_slide_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashbord_btn.setStyle("-fx-background-color: transparent");
            addMovies_btn.setStyle("-fx-background-color: #ae2d3c");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            showAddMoviesList();

        } else if (event.getSource() == availableMovies_btn) {

            dashboard_form.setVisible(false);
            dashboard_slide_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashbord_btn.setStyle("-fx-background-color: transparent");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            availableMovies_btn.setStyle("-fx-background-color: #ae2d3c");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: transparent");

            showAvailableMovies();

        } else if (event.getSource() == editScreening_btn) {

            dashboard_form.setVisible(false);
            dashboard_slide_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);

            dashbord_btn.setStyle("-fx-background-color: transparent");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: #ae2d3c");
            customers_btn.setStyle("-fx-background-color: transparent");

            showEditScreening();

        } else if (event.getSource() == customers_btn) {

            dashboard_form.setVisible(false);
            dashboard_slide_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            dashbord_btn.setStyle("-fx-background-color: transparent");
            addMovies_btn.setStyle("-fx-background-color: transparent");
            availableMovies_btn.setStyle("-fx-background-color: transparent");
            editScreening_btn.setStyle("-fx-background-color: transparent");
            customers_btn.setStyle("-fx-background-color: #ae2d3c");

            showCustomerList();
        }
    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    private void openPdf() {
        File pdfFile = new File("C:\\Users\\Admin\\Downloads\\kpo\\TicketOfficeCinema\\src\\main\\resources\\org\\example\\ticketofficecinema\\user_manual.pdf"); // Укажите путь к вашему PDF файлу

        if (pdfFile.exists()) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Открытие файлов не поддерживается на этой системе.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при открытии PDF файла.");
            }
        } else {
            System.out.println("Файл не найден: " + pdfFile.getAbsolutePath());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        displayUsername();
        showAddMoviesList();
        showEditScreening();
        comboBox();
        showAvailableMovies();
        showSpinnerValue();
        showCustomerList();
        displayTotalSoldTicket();
        displayTotalIncomeToday();
        displayTotalAvailableMovies();
        slideShow();
        manual_btn.setOnAction(event -> openPdf());

    }
}