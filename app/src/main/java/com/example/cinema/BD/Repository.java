package com.example.cinema.BD;




import com.example.cinema.R;
import com.example.cinema.Model.Film;


import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Repository {
    static List<Film> filmList = new ArrayList<>();
    public static List<Film> favoriteList = new ArrayList<>();
    private static HashMap<String, HashMap<Time, List<Boolean>>> program;
    static List<Boolean> cinemaPlaces;

    public static List<Film> getHardcodedList() {
        List<Film> filmList = Arrays.asList(
                new Film("Llamado Salvaje", "Adventure", "La tranquila vida doméstica de Buck, un perro muy amoroso, se termina de manera abrupta cuando es llevado a las inhóspitas tierras de Alaska durante la década de 1890. ", 8.6, R.drawable.llamado),
                new Film("Borat", "Comedy", "Siguiente película documental es una película estadounidense de comedia en estilo falso documental, escrita y protagonizada por Sacha Baron Cohen en su papel del periodista kazajo Borat Sagdiyev.", 7.4, R.drawable.borat),
                new Film("El cadaver", "Terror", "Como consecuencia de un desastre nuclear, una familia hambrienta es atraída a un hotel y todo que se convierte en un juego siniestro.", 4.4, R.drawable.cadaver),
                new Film("Vivo", "Terror", "Un virus arrasa con una ciudad coreana y Joon-woo intenta mantenerse a salvo encerrándose dentro de su apartamento. Justo cuando pierde la esperanza, descubre a otro superviviente. ", 6.2, R.drawable.vivo),
                new Film("El diablo a todas horas", "Suspence", "Un joven se dedica a proteger a sus seres queridos en un pueblo lleno de corrupción y personajes siniestros.", 7.5, R.drawable.eldiablo),
                new Film("Bloodshot", "Action", "Tras su muerte, Ray Garrison vuelve a la vida gracias a la corporación RST. Ahora, con un ejército de nanotecnología en sus venas, Ray es invencible.", 8, R.drawable.blood),
                new Film("Rebecca", "Romance", "Una joven recién casada llega a la imponente finca familiar de su marido y lucha contra la sombra de la primera esposa, Rebecca, cuyo legado perdura en la casa mucho después de su muerte.", 7, R.drawable.rebecca),
                new Film("La vieja guardia", "Action", "Un grupo de mercenarios, todos inmortales y con la capacidad de curarse a sí mismos, descubren que alguien sabe su secreto y deben luchar para proteger su libertad.", 8.6, R.drawable.vieja),
                new Film("The Way Back", "Sports", "Atrapado en un trabajo sin sentido y luchando contra el alcoholismo, un exatleta de secundaria recibe una oportunidad de redención cuando se convierte en el entrenador de baloncesto de su alma máter. ", 6.7, R.drawable.theway)

        );
        return filmList;
    }


    private static void markReservedPlaces(List<Integer> places) {
        for (int i : places) {
            cinemaPlaces.set(i, false);
        }
    }


    public static void addToFavorites(Film film) {

        if (!searchInFavorites(film)) {

            favoriteList.add(film);
        }

    }

    public static boolean searchInFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film))
                return true;
        }
        return false;
    }

    public static void deleteFromFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film)) {
                favoriteList.remove(f);
                return;
            }
        }
    }

    public static HashMap<String, HashMap<Time, List<Boolean>>> getHardcodedProgram() {
        if (program == null) {
            program = new HashMap<>();
            List<Film> films = getHardcodedList();
            List<List<Time>> posibilities = Arrays.asList(
                    Arrays.asList(new Time(12, 0, 0), new Time(17, 0, 0), new Time(20, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0)),
                    Arrays.asList(new Time(11, 0, 0), new Time(14, 30, 0), new Time(18, 0, 0)),
                    Arrays.asList(new Time(12, 30, 0), new Time(16, 0, 0), new Time(19, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0))

            );
            int i = 0;
            for (Film f : films) {
                List<Time> thisFilmTimes = posibilities.get(i % 5);
                i++;
                HashMap<Time, List<Boolean>> thisFilmProgram = new HashMap<>();
                for (Time t : thisFilmTimes) {
                    thisFilmProgram.put(t, Arrays.asList(true, true, true, true, true, true, true, true, true));
                }
                program.put(f.getTitle(), thisFilmProgram);
            }
        }
        return program;
    }

    /**
     * Get cinemaPlaces for selected film at selected startTime
     * @param filmTitle
     * @param time
     * @return
     */
    public static List<Boolean> getCinemaPlaces(String filmTitle, String time) {
        //transform time from string to Time obj
        Time timeObj = null;
        DateFormat formatter = new SimpleDateFormat("kk:mm");
        try {
            timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cinemaPlaces = getHardcodedProgram().get(filmTitle).get(timeObj);
        return cinemaPlaces;
    }


}
