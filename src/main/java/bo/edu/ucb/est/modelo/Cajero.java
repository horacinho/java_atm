/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est.modelo;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author ecampohermoso
 */
public class Cajero {
 private Banco banco;
 private Cliente cliente;
 private List<Cuenta> cuentas;
 private Cuenta cuenta;
 
 public Cajero(Banco banco)
 {
  this.banco=banco;
  inicioSesion();
  menuOpcion();
  
 }
 public void inicioSesion()
 {
  Scanner read = new Scanner(System.in);
  
  do
  {
   System.out.println("Ingrese su codigo de cliente: ");
   String cod=read.next();
   System.out.println(cod);
   System.out.println("Ingrese su pin: ");
   String pin=read.next();
   System.out.println(pin);
   cliente=banco.buscarClientePorCodigo(cod, pin);
   if(cliente!=null)
   {
    System.out.println("cliente encontrado");
    break;
   }
  }while(true);
 
 }
 public void menuOpcion()
 {
  Scanner read = new Scanner(System.in);
  System.out.println("1. Ver Saldo");
  System.out.println("2. Retirar dinero");
  System.out.println("3. Depositar dinero");
  System.out.println("4. Salir");
  int opcion=read.nextInt();
  if(opcion==1 || opcion==2 || opcion==3)
  {
   verCuentas(opcion);
  }
  else
  {
   inicioSesion();
  }
 }
 public void verCuentas(int accion)
 {
  Scanner read = new Scanner(System.in);
  this.cuentas=cliente.getCuentas();
  for(int i=0;i<cuentas.size();i++)
  {
   Cuenta cuenta=cuentas.get(i);
   System.out.println((i+1)+". "+cuenta.getTipo());
  }
  
  int opcion= read.nextInt();
  cuenta=cliente.getCuentas().get(opcion-1);
  if(accion==1)
  {
   System.out.println("Saldo: "+cuenta.getSaldo());
  }
  else if(accion==2)
  {
   double cantidad;
   System.out.println("Cantidad a retirar: ");
   cantidad=read.nextDouble();
   if(cuenta.retirar(cantidad))
   {
    System.out.println("Retiro exitoso");
   }
   else
   {
    System.out.println("No se pudo retirar la cantidad");
   }
  }
  else
  {
   double cantidad;
   System.out.println("Cantidad a depositar: ");
   cantidad=read.nextDouble();
   cuenta.depositar(cantidad);
  }
 }
 
}
