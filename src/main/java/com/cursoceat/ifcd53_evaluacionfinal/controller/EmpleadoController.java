package com.cursoceat.ifcd53_evaluacionfinal.controller;

import com.cursoceat.ifcd53_evaluacionfinal.modell.Empleado;
import com.cursoceat.ifcd53_evaluacionfinal.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class EmpleadoController {

    @Autowired
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     *
     * @param model
     * @return
     * @description listado de empleados
     */
    @GetMapping("/")
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "lista_empleados";
    }

    /**
     *
     * @param model
     * @return
     * @description pagina de un nuevo empleado
     */
    @GetMapping("/nuevo")
    public String nuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        listaCargoEmpleado(model);
        listaEstadoEmpleado(model);
        return "crud";
    }

    /**
     *
     * @param empleado
     * @return
     * @description crear un nuevo empleado
     */
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoRepository.save(empleado);
        return "redirect:/";
    }

    /**
     *
     * @param idEmp
     * @param model
     * @return
     * @description editar un empleado de la bbdd
     */
    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") int idEmp, Model model) {
        Empleado empleado = empleadoRepository.findById(idEmp).orElse(null);
        listaCargoEmpleado(model);
        listaEstadoEmpleado(model);
        model.addAttribute("empleado", empleado);
        return "crud";
    }

    /**
     *
     * @param idEmp
     * @return
     * @description eliminar un empleado de la bbdd
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") int idEmp) {
        empleadoRepository.deleteById(idEmp);
        return "redirect:/lista_empleados";
    }

    /**
     *
     * @param model
     * @description listado de cargos posibles del empleado
     */
    public void listaCargoEmpleado(Model model) {
        ArrayList<String> cargoEmpleado = new ArrayList<>();
        cargoEmpleado.add("");
        cargoEmpleado.add("Gerente");
        cargoEmpleado.add("Vendedor");
        cargoEmpleado.add("Oficinista");
        cargoEmpleado.add("Jefe");
        model.addAttribute("cargoEmpleado", cargoEmpleado);
    }

    /**
     *
     * @param model
     * @description listado de los estados posibles del empleado
     */
    public void listaEstadoEmpleado(Model model) {
        ArrayList<String> estadoEmpleado = new ArrayList<>();
        estadoEmpleado.add("");
        estadoEmpleado.add("Contratado");
        estadoEmpleado.add("Fijo");
        estadoEmpleado.add("Practicas");
        estadoEmpleado.add("Desempleado");
        model.addAttribute("estadoEmpleado", estadoEmpleado);
    }

}
