package com.data.session02.controller;

import com.data.session02.model.entity.ShowTime;
import com.data.session02.model.entity.Movie;
import com.data.session02.model.entity.ScreenRoom;
import com.data.session02.model.entity.Theater;
import com.data.session02.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("showtimes")
public class ShowTimeController {

    @Autowired
    private IService<ShowTime, Long> showTimeService;

    @Autowired
    private IService<Movie, Long> movieService;

    @Autowired
    private IService<ScreenRoom, Long> screenRoomService;

    @GetMapping
    public String listShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(required = false) Long theaterId,
            @RequestParam(required = false) Long screenRoomId,
            Model model) {

        List<ShowTime> allShowtimes = showTimeService.findAll();

        List<ShowTime> filteredShowtimes = allShowtimes.stream()
                .filter(st -> movieId == null || (st.getMovie() != null && st.getMovie().getId().equals(movieId)))
                .filter(st -> {
                    if (date == null || st.getStartTime() == null) return true;

                    // So sánh ngày (bỏ phần giờ)
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(st.getStartTime());
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.set(Calendar.SECOND, 0);
                    cal1.set(Calendar.MILLISECOND, 0);

                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(date);
                    cal2.set(Calendar.HOUR_OF_DAY, 0);
                    cal2.set(Calendar.MINUTE, 0);
                    cal2.set(Calendar.SECOND, 0);
                    cal2.set(Calendar.MILLISECOND, 0);

                    return cal1.getTime().equals(cal2.getTime());
                })
                .filter(st -> theaterId == null || (st.getScreenRoom() != null &&
                        st.getScreenRoom().getTheater() != null &&
                        st.getScreenRoom().getTheater().getId().equals(theaterId)))
                .filter(st -> screenRoomId == null || (st.getScreenRoom() != null &&
                        st.getScreenRoom().getId().equals(screenRoomId)))
                .collect(Collectors.toList());

        model.addAttribute("showtimes", filteredShowtimes);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());

        List<Theater> theaters = screenRoomService.findAll().stream()
                .map(ScreenRoom::getTheater)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("theaters", theaters);

        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedDate", date);
        model.addAttribute("selectedTheaterId", theaterId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);

        return "showtime-list";
    }




    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("showtime", new ShowTime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ShowTime showTime) {
        showTimeService.save(showTime);
        return "redirect:/showtimes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<ShowTime> showTime = showTimeService.findById(id);
        if (showTime.isPresent()) {
            model.addAttribute("showtime", showTime.get());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "showtime-edit";
        }
        return "redirect:/showtimes";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ShowTime showTime) {
        showTimeService.update(id, showTime);
        return "redirect:/showtimes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        showTimeService.delete(id);
        return "redirect:/showtimes";
    }
}
