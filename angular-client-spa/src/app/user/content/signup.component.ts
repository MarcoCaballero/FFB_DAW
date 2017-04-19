import { Component, OnInit } from '@angular/core';




@Component({
    moduleId: module.id,
    selector: 'ffbcomp-signup',
    templateUrl: 'signup.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class SignupComponent implements OnInit {


    name = '';
    surname = '';
    secondSurname = '';
    dni = '';
    email = '';
    city = '';
    home = '';
    telephone = '';
    firstPassword = '';
    secondPassword = '';


    title = 'Registro';

    
    constructor() { }

    sendSingUp(){
       if(this.name === ""){
            this.name = "Este campo no puede estar vacío";
       }
       if(this.surname === ""){
           this.surname = "Este campo no puede estar vacío";
       }
       if(this.secondSurname === ""){
           this.secondSurname = "Este campo no puede estar vacío";
       }
       if(this.dni === ""){
           this.dni = "Este campo no puede estar vacío";
       }
       if(this.city === ""){
           this.city = "Este campo no puede estar vacío";
       }
        if(this.email === ""){
           this.email = "Este campo no puede estar vacío";
       }
       if(this.home === ""){
           this.home = "Este campo no puede estar vacío";
       }
       if(this.telephone === ""){
           this.telephone = "Este campo no puede estar vacío";
       }
       if(this.firstPassword === ""){
           this.firstPassword = "Este campo no puede estar vacío";
       }
       if(this.secondPassword === ""){
           this.secondPassword = "Este campo no puede estar vacío";
       }
       if(this.firstPassword !== this.secondPassword){
           this.firstPassword = "not idem";
           this.secondPassword = "not idem";
       }
       
    }
    isValidSurname(){
        if(this.surname !== "" && this.surname !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidName(){
        if(this.name !== "" && this.name !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidName(){
        if(this.name === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidSurname(){
        if(this.surname === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidSecondsurname(){
        if(this.secondSurname !== "" && this.secondSurname !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidSecondsurname(){
        if(this.secondSurname === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidDni(){
        if(this.dni !== "" && this.dni !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidDni(){
        if(this.dni === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidEmail(){
        if(this.email !== "" && this.email !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidEmail(){
        if(this.email === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidCity(){
        if(this.city !== "" && this.city !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidCity(){
        if(this.city === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidHome(){
        if(this.home !== "" && this.home !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidHome(){
        if(this.home === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isValidTelephone(){
        
        if(this.telephone !== "" && this.telephone !== "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidTelephone(){
        if(this.telephone === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidFirstPass(){
        if(this.firstPassword === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }

    isInvalidSecondPass(){
        if(this.secondPassword === "Este campo no puede estar vacío"){
            return true;
        }
        return false;
    }
    
    
    ngOnInit() { }
}
