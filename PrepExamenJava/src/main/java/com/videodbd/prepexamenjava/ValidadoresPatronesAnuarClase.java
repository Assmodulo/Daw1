package com.videodbd.prepexamenjava;

public class ValidadoresPatronesAnuarClase {

    //Con estos validadores tengo ejemplos más que suficientes.
    private boolean validateName(String name){
        return (name.length() > 3 && name.matches("[A-Z]{1}[a-z]{2,25}"));
    }

    private boolean validatePhone(String phone){
        return phone.matches("[1-9]{9}");
    }

    private boolean validateEmail(String email){
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailPattern);
    }

    private boolean validateAge(String age){
        return age.matches("[1-9]{1,3}");
    }

    private boolean validateDni(String dni){
        return dni.matches("[0-9]{7,8}[A-Z a-z]");
    }
}
