import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class pruebaDos {
	public static void main (String[] args) throws IOException{
		String opcion = "";
		String DatosAlumnos[][] = new String [20][7];
		Scanner sc = new Scanner(System.in);
		double NotasAlumnos[][] = new double [20][7];
		int i=0 , j=0, contarCupos = 0;
		for (i=0 ; i<= 19; i++) {
			for (j=0 ; j<7 ; j++) {
				DatosAlumnos[i][j]="";
				NotasAlumnos[i][j]=0;
			}
		}
		while(!opcion.equals("7")) {
			System.out.println("Menú Principal \n");
			System.out.println("Ingrese una opción del 1 al 7: ");
			System.out.println("1 - Registar datos de alumnos");
			System.out.println("2 - Agregar o modificar notas según rut de alumno ");
			System.out.println("3 - Buscar datos de un alumno");
			System.out.println("4 - Modificar datos de un alumno según rut");
			System.out.println("5 - Eliminar alumno según rut");
			System.out.println("6 - Revisar Reporte");
			System.out.println("7 - Salir");
			opcion = sc.nextLine();
			switch(opcion) {
			case "1": menuAgregarDatos(NotasAlumnos,DatosAlumnos,contarCupos) ; break;
			case "2":modificarNotas(NotasAlumnos,DatosAlumnos);break;
			case "3":BuscarAlumno(DatosAlumnos,NotasAlumnos);	break;
			case "4":menuModificarDatos(DatosAlumnos,escribirRutTexto(DatosAlumnos));break;
			case "5": eliminarUsuario(DatosAlumnos,NotasAlumnos,contarCupos); break;
			case "6":registro(DatosAlumnos,NotasAlumnos); break;
			case "7": System.out.println("Adios"); break; 
			default: System.out.println("Ingrese una opción válida, del 1 al 7"); break; 
			}
		}
	}
	public static void menuAgregarDatos(double NotasAlumnos[][], String DatosAlumnos[][], int contador){
		String opcion = "";
		Scanner sc = new Scanner(System.in);
		while(!opcion.equals("2")) {
			System.out.println("Menú Agregar Datos \n");
			System.out.println("Ingrese una opción 1 ó 2: ");
			System.out.println("1 - Agregar un nuevo dato");
			System.out.println("2 - Salir");
			opcion = sc.nextLine();
			switch(opcion) {
			
			case "1": agregarDatosAlumno(NotasAlumnos,DatosAlumnos,contador);  break;
			
			case "2": break;
			
			default: System.out.println("Ingrese una opción válida, 1 ó 2"); break; 
			}
		}
	}
	public static void menuModificarDatos(String arreglo[][],String rut) throws IOException{
		String opcion= "";
		boolean validarOp = false;
		Scanner sc = new Scanner(System.in);
		if(existeRut(rut,arreglo)==true) {
		while(!opcion.equals("7")) {
			System.out.println("Menú - Modifcar Datos \n");
			System.out.println("Ingrese una opción del 1 al 7: ");
			System.out.println("1 - Modificar Nombre");
			System.out.println("2 - Modificar Apellido Materno");
			System.out.println("3 - Modificar Apellido Paterno");
			System.out.println("4 - Número de contacto");
			System.out.println("5 - Correo electrónico");
			System.out.println("6 - Fecha de Nacimiento");
			System.out.println("7 - Salir");
			opcion = sc.nextLine();
			switch(opcion) {
			case "1": modificarDatos(arreglo,1,rut); break;
			case "2": modificarDatos(arreglo,2,rut); break;
			case "3": modificarDatos(arreglo,3,rut); break;
			case "4": modificarDatos(arreglo,4,rut); break;
			case "5": modificarDatos(arreglo,5,rut); break;
			case "6": modificarDatos(arreglo,6,rut); break;
			case "7": break;
			default: System.out.println("Ingrese una opción válida, 1 a 7 \n"); break; 
			}
		}
		}
	}
	public static void agregarDatosAlumno(double NotasAlumnos[][], String DatosAlumnos[][], int contador) {
		Scanner sc = new Scanner(System.in);
		String rut,nombre,apellido1,apellido2,numContacto,eMail,fNacimiento,subRut,subNombre;
		int i,j,control,variableParaRut, inicio;
		boolean IDValido,abcValido,numValido;
		IDValido= true;
		subRut="";
		variableParaRut=0;
		inicio=0;
		while(DatosAlumnos[inicio][0]!=""){
			inicio++;
		}
		do{
			IDValido= true;
			do{
				IDValido= true;
				System.out.println("Ingresa el RUN/RUT del alumno (RUN/RUT sin punto ni digito verificador)");
				rut = sc.nextLine();
				if(cadenaEsNumero(rut)==false || rut.isEmpty()) {
					IDValido = false;
				}
			}while(!cadenaEsNumero(rut) || rut.isEmpty());
		//Verifica ID no exista
		for (i = 0 ; i<20; i++) {
			if(DatosAlumnos[i][0].equals(rut)){
				System.out.println("El RUN/RUT ingresado ya existe");
				IDValido = false;
			}
		}
		}while(!IDValido);
		
		if(IDValido){
			//Nombre alumno
			NotasAlumnos[inicio][0]= Double.parseDouble(rut);
			DatosAlumnos[inicio][0]= rut;
			contador++;
			do {
				System.out.println("Ingresa el nombre del alumno");
				nombre = sc.nextLine();
			}while (!cadenaABC(nombre) || nombre.isEmpty());
			DatosAlumnos[inicio][1]=nombre;
			// Apellido Paterno
			do {
				System.out.println("Ingresa el apellido paterno de : "+ DatosAlumnos[inicio][1]);
				apellido1 = sc.nextLine();
			}while (!cadenaABC(apellido1) || apellido1.isEmpty());
			DatosAlumnos[inicio][2]=apellido1;
			// Apellido Materno
			do {
				System.out.println("Ingresa el apellido materno de: "+DatosAlumnos[inicio][1]);
				apellido2 = sc.nextLine();
			}while (!cadenaABC(apellido2) || apellido2.isEmpty());
			DatosAlumnos[inicio][3]=apellido2;
			//Contacto
			do {
				numValido = true;
				System.out.println("Ingrese numero de contacto de: "+DatosAlumnos[inicio][1]);
				System.out.print("+56");
				numContacto = sc.nextLine();
				for (i=0;i<numContacto.length();i++) {
					subNombre= numContacto.substring(i,i+1);
					if(esNumero(subNombre)==false){
						numValido=false;
					}
				}
				if(numContacto.length()!=9 || numContacto.isEmpty()) {
					numValido=false;
				}
			}while (!numValido);
			DatosAlumnos[inicio][4]=numContacto;
			
			//Correo electronico+
			do {
			System.out.println("Ingrese correo electronico de: "+DatosAlumnos[inicio][1]);
			eMail = sc.next();		
			}while(!validaEmail(eMail) || eMail.isEmpty());
			DatosAlumnos[inicio][5]= eMail;
			//Fecha de nacimiento
		    System.out.println("Ingresa fecha de nacimiento del alumno: " +DatosAlumnos[inicio][1] +" (con el formato dd/mm/yyyy) \n Ejemlo: 11/11/2011");
			fNacimiento = validaFecha() ;
			DatosAlumnos[inicio][6]= fNacimiento;
		}
		System.out.println("");
	}

	public static boolean cadenaEsNumero(String cadena) {
		String subNombre = "";
		boolean abcValido = true;
		for(int i=1; i< cadena.length();i++) {
			subNombre=(cadena.substring(i,i+1));
			if(esNumero(subNombre)==false){
				abcValido = false;
			}
		}
		return abcValido;
	}
	// Revisar registro de alumno
	public static void registro(String DatosAlumnos[][] , double NotasAlumnos[][]) {
		String nombre, estudiante;
		boolean existeCadenaTexto = false;
		double promedio = 0;
		Scanner sc = new Scanner (System.in);
		System.out.println("Ingresa tu nombre");
		nombre = sc.nextLine();
		System.out.println("Ingresa nombre del estudiante para revisar su Reporte");
		estudiante = sc.nextLine();
		for(int i=0; i<20 ; i++) {
			if(DatosAlumnos[i][1].equals(estudiante)) {
			mostrarNotas(NotasAlumnos,DatosAlumnos[i][0]);
			existeCadenaTexto = true;
			promedio = calcularPromedio(NotasAlumnos,DatosAlumnos[i][0]);
			if(promedio>0) {
				if(promedio>3.9) {
					System.out.println("El alumno "+DatosAlumnos[i][1] + " tiene un promedio " +promedioATexto(promedio)+" ("+promedio+") y se encuentra APROBADO, revisado por "+ nombre);
				} else {
					System.out.println("El alumno "+DatosAlumnos[i][1] + " tiene un promedio " +promedioATexto(promedio)+" ("+promedio+") y se encuentra REPROBADO, revisado por "+ nombre);
				}
			}
			}
		}
		if (existeCadenaTexto==false) {
			System.out.println("El dato ingresaso no esta registrado");
		}
	}
	//Ver si letra es Numero
	public static boolean esNumero(String letra) {
		Pattern patronMail = Pattern.compile("^[0-9]$");
		Matcher matcher = patronMail.matcher(letra);
		return matcher.matches();
	}
	//Mostrar Notas
	public static void mostrarDatos(String DatosAlumnos[][], int posicion, String cadenaTexto,double NotasAlumnos[][]) {
		boolean existeCadenaTexto = false;
		for ( int i = 0; i<20 ; i++) {
			if (DatosAlumnos[i][posicion].toLowerCase().equals(cadenaTexto.toLowerCase())){
				System.out.println("ID: "+ DatosAlumnos[i][0]);
				System.out.println("Nombre: "+ DatosAlumnos[i][1]);
				System.out.println("Apellido paterno: "+ DatosAlumnos[i][2]);
				System.out.println("Apellido materno: "+ DatosAlumnos[i][3]);
				System.out.println("Número de contacto: "+ DatosAlumnos[i][4]);
				System.out.println("Correo: "+ DatosAlumnos[i][5]);
				System.out.println("Fecha de nacimiento: "+ DatosAlumnos[i][6]);
				mostrarNotas(NotasAlumnos, DatosAlumnos[i][0]);
				existeCadenaTexto = true;
			}
		}
		if (existeCadenaTexto == false) {
			System.out.println("El dato ingresado no esta registrado");
		}
	}
	public static boolean esAbc(String letra) {
		Pattern patronMail = Pattern.compile("^[a-zA-Z]$");
		Matcher matcher = patronMail.matcher(letra);
		return matcher.matches();
		}
	
	public static boolean esNota(String letra) {
		Pattern patronMail = Pattern.compile("^([0-9]+[.])*[0-9]$");
		Matcher matcher = patronMail.matcher(letra);
		return matcher.matches();
	}
	//1 
	public static boolean validarEsNota(String nota) {
			int count;
			int i;
			boolean retorno;
			boolean validador;
			
			validador = false;
			count = 0;
		
			if (nota.length()<=3) {
				for (i=0;i<=nota.length()-1;i++) {
					if (esNota(nota.substring(i,i+1))==true && count<=1) {
						validador = true;
						if (nota.substring(i,i+1).equals(".")) {
							count = count+1;
						}
					} else {
						validador = false;
					}
				}
				if ((validador)==true && count<=1) {
					// ConvertiANumero
					if ((Double.parseDouble(nota)>=1 && Double.parseDouble(nota)<=7)) {
						validador = true;
					} else {
						validador = false;
					}
				} else {
					validador = false;
				}
			}
			retorno = validador;
			return retorno;
		}

	//2

	public static String promedioATexto(double promedio) {
			String coma;
			String decimales[];
			int i;
			int j;
			int numer[];
			String textopromedio;
			String unidades[];
			numer = new int[2];
			unidades = new String[9];
			decimales = new String[2];
			coma = "";
			textopromedio = "";
			numer[0] = 0;
			numer[1] = 0;
			unidades[0] = "uno";
			unidades[1] = "dos";
			unidades[2] = "tres";
			unidades[3] = "cuatro";
			unidades[4] = "cinco";
			unidades[5] = "seis";
			unidades[6] = "siete";
			unidades[7] = "ocho";
			unidades[8] = "nueve";
			
			numer[0] = Integer.valueOf(Double.toString(promedio).substring(0,1));
			coma = Double.toString(promedio).substring(1,2);
			numer[1] = Integer.valueOf(Double.toString(promedio).substring(2,3));
			switch (numer[0]) {
			case 1:
				textopromedio = textopromedio.concat(unidades[0]);
				break;
			case 2:
				textopromedio = textopromedio.concat(unidades[1]);
				break;
			case 3:
				textopromedio = textopromedio.concat(unidades[2]);
				break;
			case 4:
				textopromedio = textopromedio.concat(unidades[3]);
				break;
			case 5:
				textopromedio = textopromedio.concat(unidades[4]);
				break;
			case 6:
				textopromedio = textopromedio.concat(unidades[5]);
				break;
			default:
				textopromedio = textopromedio.concat(unidades[6]);
			}
			textopromedio = textopromedio.concat(" ");
			if ((coma.equals("."))) {
				textopromedio = textopromedio.concat("coma ");
			}
			
			switch (numer[1]) {
			case 1:
				textopromedio = textopromedio.concat(unidades[0]);
				break;
			case 2:
				textopromedio = textopromedio.concat(unidades[1]);
				break;
			case 3:
				textopromedio = textopromedio.concat(unidades[2]);
				break;
			case 4:
				textopromedio = textopromedio.concat(unidades[3]);
				break;
			case 5:
				textopromedio = textopromedio.concat(unidades[4]);
				break;
			case 6:
				textopromedio = textopromedio.concat(unidades[5]);
				break;
			case 7:
				textopromedio = textopromedio.concat(unidades[6]);
				break;
			case 8:
				textopromedio = textopromedio.concat(unidades[7]);
				break;
			default:
				textopromedio = textopromedio.concat(unidades[8]);
			}
			return textopromedio;
			
		}

	public static void modificarDatos(String datosAlumnos[][], int posicion, String rut) throws IOException {
		BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
		int i;
		boolean correcto = false;
		String ingreso ="";
		int j;
		for (i=0;i<=19;i++) {
			if (datosAlumnos[i][0].equals(rut)) {
				System.out.println("El dato anterior es :"+datosAlumnos[i][posicion]);
				do {
					System.out.println("Ingrese nuevo valor:");
					
				switch(posicion){
				 case 1: ingreso =bufEntrada.readLine(); 
					 if(cadenaABC(ingreso) && !ingreso.isEmpty()) {
					       correcto = true;} break;
					       
				 case 2: ingreso =bufEntrada.readLine(); 
					 if(cadenaABC(ingreso) && !ingreso.isEmpty()) {
				       correcto = true;} break;
				       
				 case 3: ingreso =bufEntrada.readLine(); 
					 if(cadenaABC(ingreso) && !ingreso.isEmpty()) {
				       correcto = true;} break;
				       
				 case 4: System.out.print("+56");
					 ingreso =bufEntrada.readLine(); 
					 if(cadenaEsNumero(ingreso) && ingreso.length()==9 && !ingreso.isEmpty()) {
				       correcto = true;} break;
				       
				 case 5: ingreso =bufEntrada.readLine(); 
					 if(validaEmail(ingreso) && !ingreso.isEmpty()) {
					 correcto = true;} break;
				
					 
				case 6: 
					if(!ingreso.isEmpty()) {ingreso =validaFecha(); correcto = true;} break;
				}
				} while(!correcto);
				
				datosAlumnos[i][posicion] = ingreso;		
				
			}
			}
	}


//4
	public static String escribirRutTexto(String arreglo[][]) throws IOException {
		BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
		String ruTexto;
		ruTexto = "";
		System.out.println("Ingresa el RUN/RUT del alumno (RUN/RUT sin punto ni digito verificador");
		ruTexto = bufEntrada.readLine();
		if ((cadenaEsNumero(ruTexto))) {
			if (existeRut(ruTexto,arreglo)==false) {
				System.out.println("¡Rut no encontrado!");
			}
		} else {
			System.out.println("Ingrese sólo números");
		}
		return ruTexto;
		
	}
	public static void mostrarNotas (double notasAlumnos[][], String IdAlumnos) {
		int i,j=0;
		for (i=0;i<20;i++) {
			if (notasAlumnos[i][0]==Double.parseDouble(IdAlumnos)) {
				for (j=1;j<7;j++) {
					System.out.println(" La nota "+j+" es: "+ notasAlumnos[i][j]);
				}	
			}
		}
	}
		
		public static boolean cadenaABC(String letra) {
			int control=0;
			String subNombre="";
			boolean abcValido=true;
			for (control=0;control<letra.length();control++) {
				subNombre=letra.substring(control,control+1);
				if (esAbc(subNombre)==false) {
					abcValido=false;
				}
			}
			return abcValido;
		}
		public static boolean existeRut(String rut,String datosAlumnos[][]) {
		int i=0;
		boolean retorno = false;
		for (i=0;i<20;i++){
			if (datosAlumnos[i][0].equals(rut)) {
			retorno=true;
			}
		}
		return retorno;
		}

		public static void iniciarArreglos(String datosAlumnos[][],double notasAlumnos[][] ) {
			int i,j=0;
			for (i=0;i<20;i++) {
				for (j=0;j<7;j++) {
				datosAlumnos[i][j]="";
				notasAlumnos [i][j]=0;
				}
				
			}
			
		}
		public static void BuscarAlumno(String DatosAlumnos[][],double NotasAlumnos[][]) throws IOException {
			String opciontexto;
			int opcion;
			boolean validarOp;
			opcion=0;
			validarOp=false;
			Scanner sc = new Scanner(System.in);
			
			while(opcion!=4) {
				System.out.println("");
				System.out.println("Menú Busqueda de Alumno");
				System.out.println("");
				System.out.println("Ingrese opción del 1 al 4");
				System.out.println("1 - Buscar por Nombre");
				System.out.println("2 - Buscar por Apellido Paterno");
				System.out.println("3 - Buscar por Rut");
				System.out.println("4 - Salir");
				
				do {
					opciontexto = sc.nextLine();
					if(esNumero(opciontexto)==false) {
						System.out.println("¡Error de valor! - ingrese un número entre 1 y 4");
					}else {
						if(Integer.parseInt(opciontexto)<1 || Integer.parseInt(opciontexto)>4) {
							System.out.println("¡Error de valor! - ingrese un número entre 1 y 4");
						}else {
							opcion=Integer.parseInt(opciontexto);
							validarOp=true;
						}
					}
					
				}while(esNumero(opciontexto)==false || validarOp==false);
				
				switch (opcion) {
				  case 1: System.out.println("Ingrese Nombre del alumno");
				  opciontexto = sc.nextLine();
				  mostrarDatos(DatosAlumnos,1,opciontexto,NotasAlumnos);
				  break;
				  case 2: System.out.println("Ingrese Apellido Paterno del alumno");       
				  opciontexto = sc.nextLine();
		          mostrarDatos(DatosAlumnos,2,opciontexto,NotasAlumnos);
		          break;
				  case 3: mostrarDatos(DatosAlumnos,0,escribirRutTexto(DatosAlumnos),NotasAlumnos);
				  break;
				  default: opcion=4;
				  break;
				}
			  }
		}
			public static double calcularPromedio( double NotasAlumnos[][], String IDAlumnos) {
				
				int contador,i,j;
				double Promedio,Suma;
				contador=0;
				Suma=0;
				
				for(i=0;i<20;i++) {
					if(NotasAlumnos[i][0]==Double.parseDouble(IDAlumnos)) {
						for(j=1;j<7;j++) {
							if(NotasAlumnos[i][j]>0) {
								Suma=Suma+NotasAlumnos[i][j];
								contador = contador+1;
							}
						}
					}
				}
				if(contador==6) {
					Promedio=(Suma/contador);
					return Promedio;
				}else {
					System.out.printf("Falta Ingresar %s Notas \n", (6-contador));
					return 0;
				}
			}
		
	
		public static void eliminarUsuario(String DatosAlumnos[][], double NotasAlumnos[][], int contar) {
			int i,indiceD,j,indiceN;
			String rut,eleccion;
			boolean IDencontrado;
			IDencontrado = false;
			indiceD=0;
			indiceN=0; 
			Scanner sc = new Scanner(System.in);
			System.out.println("Ingresa el RUN/RUT del alumno que desea eliminar (RUN/RUT sin punto ni digito verificador) ");
			do {
				rut = sc.nextLine();
				if(cadenaEsNumero(rut) == false) {
					System.out.println("Ingrese RUN/RUT sin punto ni digito verificador");
				}
			} while(!cadenaEsNumero(rut));
			
			for(i=0; i<20; i++){
				if(DatosAlumnos[i][0]==rut) {
					indiceD=i;
					IDencontrado=true;
				}
			}
			for(i=0;i<20;i++){
				if(NotasAlumnos[i][0]==Integer.parseInt(rut)) {
					indiceN=i;
					IDencontrado=true;
				}
			}
			
			if(IDencontrado == true) {
				System.out.printf("Estas seguro que deseas eliminar el alumno con ID %s \n",rut);
				do {
				System.out.println("1-Si");
				System.out.println("2-No");
				
				eleccion = sc.nextLine();
				
				if(eleccion.equals("1")) {
					for(j=0;j<7;j++) {
						DatosAlumnos[indiceD][j]="";
					}
					for(j=0;j<7;j++) {
						NotasAlumnos[indiceN][j]=0;
					}
					System.out.println("Se eliminó el usuario exitosamente");
					contar = contar + 1; 
				}	
				} while(!eleccion.equals("1") && !eleccion.equals("2"));
				
				
			}else {
				System.out.println("El dato ingresado no está registrado");
			}
		}
/*
			public static String EscribirRutDouble(double NotasAlumnos[][]) {
				double rut;
				String ruTexto;
				ruTexto="";
				rut=0;
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Ingresar el RUN/RUT del alumno(RUN/RUT sin punto ni digito verificador)");
				ruTexto= sc.nextLine();
				if(cadenaEsNumero(ruTexto)) {
					rut = Double.parseDouble(ruTexto);
					if(existeRut(rut,NotasAlumnos[][])==false) {
						System.out.println("¡Rut no encontrado!");
					}
					
				}else {
					System.out.println("Ingrese sólo números");
				}
				return ruTexto;
			 }

*/
			public static void modificarNotas(double NotasAlumnos[][], String DatosAlumnos[][]) throws IOException{
				 
			    String IDAlumnos, decision,modNot,nuevaNot;
			    int posAlum =0;
			    boolean esNota;
			    
			    Scanner sc = new Scanner(System.in);

			    esNota = false;
			    decision = "";
			    System.out.println(DatosAlumnos[0][0]);
			    IDAlumnos = escribirRutTexto(DatosAlumnos);
			    
			    if (existeRut(IDAlumnos,DatosAlumnos)){
			        do{
			            for(int i= 0; i<20 ; i++){
			                if (NotasAlumnos[i][0]==Integer.parseInt(IDAlumnos)){
			                    System.out.printf("Las notas del alumno %s %s %s son: \n",DatosAlumnos[i][1],DatosAlumnos[i][2],DatosAlumnos[i][3]);
			                    for (int j=1; j<7; j++){
			                        System.out.println("La nota numero " + j + " es: " + NotasAlumnos[i][j]);
			                        posAlum = i;
			                    }
			                }
			            }
			            do {
			                System.out.println("¿Que nota deseas modificar?");
			                modNot = sc.nextLine();
			                if(esNumero(modNot)){
			                    if(Integer.parseInt(modNot) < 1 || Integer.parseInt(modNot) > 7){
			                        System.out.println("ingrese una nota valida");
			                    } 
			                }
			                if(esNumero(modNot)==false){
			                     System.out.println("ingrese una nota valida"); 
			                }
			            } while(esNumero(modNot)==false || Integer.parseInt(modNot) < 1 || Integer.parseInt(modNot) > 7);
			            if(esNumero(modNot)){
			                if (Integer.parseInt(modNot) >= 1 & Integer.parseInt(modNot)<=7){
			                    System.out.println("¿Por cual nota quieres reemplazarla?");
			                    do{
			                        nuevaNot = sc.nextLine();
			                        esNota = validarEsNota(nuevaNot);
			                        if(esNota == false){
			                            System.out.println("Ingrese una nota valida entre 1.0 - 7.0");
			                        }
			                    }while(esNota==false);

			                    NotasAlumnos[posAlum][Integer.parseInt(modNot)]=Double.parseDouble(nuevaNot);
			                    System.out.printf("La nota numero %s ha sido reemplazada por un %s \n",modNot,nuevaNot);
			                    do{
			                        
			                            System.out.println("¿Deseas Modificar otra nota?");
			                            System.out.println("1-Si");
			                            System.out.println("2-No");
			                            decision = sc.nextLine();
			                            if(!decision.equals("1") && !decision.equals("2")){
			                                System.out.println("Ingrese una opcion valida");
			                            } 
			                      
			                    }while(!decision.equals("1") && !decision.equals("2"));
			  
			                }
			                else {
			                    System.out.println("Ingrese una nota valida");
			               }
			                
			            }
			            else {
			                System.out.println("Ingrese una nota valida");
			            }
			        } while(!decision.equals("2"));
			    }
			}
			public static Boolean validaEmail(String email) {
				Pattern patronMail = Pattern.compile("^([0-9a-zA-Z]+[._])*[0-9a-zA-Z]+@([0-9a-zA-Z]+[.])+[a-zA-Z]{2,3}$");
				Matcher matcher = patronMail.matcher(email);
				return matcher.matches();
			}
		
		public static String validaFecha () {
			Pattern patronFecha = Pattern.compile("^[0-9]{2}+/[0-9]{2}/+[0-9]{4}$");
			Scanner sc = new Scanner(System.in);
			String dia,mes,anio;
			String fecha;
			boolean formatoValido,fechaValida;
			fechaValida = false;
			mes ="";
			dia="";
			anio="";
			do {
			fecha = sc.nextLine();
			Matcher matcher = patronFecha.matcher(fecha);
			formatoValido=matcher.matches();	
			if(formatoValido==false) {
				System.out.println("El formato es incorrecto, intentalo de nuevo");
			} else {
			
			if(formatoValido==true) {
				dia = fecha.substring(0,2);
				mes = fecha.substring(3,5);
				anio = fecha.substring(6,10);
				fechaValida = true;
			}
			if (mes.equals("02")) {
				if (Integer.parseInt(dia)>29) {
					System.out.println("Fecha incorrecta, intentalo de nuevo");
					fechaValida = false;
			}
			}
			if (Integer.parseInt(dia)>31) {
				System.out.println("Formato día incorrecto, intentalo de nuevo");
				fechaValida = false;
			}
			if (Integer.parseInt(mes)>12) {
				System.out.println("Formato mes incorrecto, intentalo de nuevo");
				fechaValida = false;
				
			}
			if (Integer.parseInt(anio)<=1875 || Integer.parseInt(anio)>2021) {
				System.out.println("Formato año incorrecto, intentalo de nuevo");
				fechaValida = false;
				
			}
			}
			}while(fechaValida==false);
			
			return fecha;
	 
			
	 }

}

