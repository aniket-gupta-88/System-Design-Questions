import controllers.BookingController;
import controllers.TheatreController;
import entities.*;
import enums.City;
import enums.SeatCategory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookMyShowApp {

    private TheatreController theatreController;
    private BookingController bookingController;

    public static void main(String[] args){
        BookMyShowApp app = new BookMyShowApp();
        app.initialize();
        app.userFlow();
    }

    private void initialize(){
        theatreController = new TheatreController();
        bookingController = new BookingController();

        Movie baahubali = new Movie("BAAHUBALI");
        Movie avengers = new Movie("AVENGERS");

        Screen inoxScreen1 = new Screen(1, createSeats());
        Theatre inoxTheatreBangalore = new Theatre(
                "INOX",
                City.BANGALORE,
                List.of(inoxScreen1)
        );

        Screen pvrScreen1 = new Screen(1,createSeats());
        Theatre pvrTheatreDelhi = new Theatre(
                "PVR",
                City.DELHI,
                List.of(pvrScreen1)
        );

        theatreController.addTheatre(inoxTheatreBangalore);
        theatreController.addTheatre(pvrTheatreDelhi);

        Show inoxMorningShowToday = new Show(
                baahubali,
                inoxScreen1,
                LocalDate.now(),
                LocalTime.of(8,0)
        );

        Show inoxAfternoonShowToday = new Show(
                baahubali,
                inoxScreen1,
                LocalDate.now(),
                LocalTime.of(15, 0)
        );

        Show inoxEveningShowToday = new Show(
                avengers,
                inoxScreen1,
                LocalDate.now(),
                LocalTime.of(18, 0)
        );


        Show pvrMorningShowTomorrow = new Show(
                baahubali,
                pvrScreen1,
                LocalDate.now().plusDays(1),
                LocalTime.of(9, 0)
        );

        inoxScreen1.addShow(inoxMorningShowToday);
        inoxScreen1.addShow(inoxAfternoonShowToday);
        inoxScreen1.addShow(inoxEveningShowToday);
        pvrScreen1.addShow(pvrMorningShowTomorrow);
    }

    private void userFlow(){

        User user = new User("U1", "User1");
        System.out.println("User logged in : User1");

        City selectedCity = City.BANGALORE;
        System.out.println("Selected City: " + selectedCity);

        LocalDate selectedDate =  LocalDate.now();
        System.out.println("Selected Date: " + selectedDate);

        Set<Movie> movies = theatreController.getMovies(selectedCity, selectedDate);
        System.out.println("Movies available");
        movies.forEach(m -> System.out.println(" - " + m.getName()));

        Movie selectedMovie = movies.iterator().next();
        System.out.println("Selected Movie: " + selectedMovie.getName());

        List<Theatre> theatres = theatreController.getTheatres(selectedCity, selectedMovie, selectedDate);
        System.out.println("Theatres available");
        theatres.forEach(t -> System.out.println(" - " + t.getName()));

        Theatre selectedTheatre = theatres.get(0);
        System.out.println("Selected Theatre: " + selectedTheatre.getName());

        List<Show> shows = theatreController.getShows(
                selectedMovie,
                selectedDate,
                selectedTheatre
        );
        System.out.println("Shows available");
        shows.forEach(s ->
                System.out.println(" - " + s.getStartTime()));

        Show selectedShow = shows.get(0);
        System.out.println("Selected Show Time: " + selectedShow.getStartTime());

        List<Integer> selectedSeats = List.of(1,2,3);
        System.out.println("Selected Seats: " + selectedSeats);

        Booking booking =
                bookingController.createBooking(
                        user,
                        selectedShow,
                        selectedSeats
                );

        System.out.println("BOOKING SUCCESSFUL");
        System.out.println("Booking ID: " + booking.getBookingId());
    }

    private List<Seat> createSeats(){
        List<Seat> seats = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            seats.add(new Seat(i, SeatCategory.SILVER));
        }
        return seats;
    }

}
