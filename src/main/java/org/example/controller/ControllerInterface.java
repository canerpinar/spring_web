package org.example.controller;


import org.springframework.ui.Model;
import java.util.Locale;
public interface ControllerInterface {
    String index(Locale locale, Model model);
}
