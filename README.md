# java_mail
Aplicacion experimental sobre flujo de correos de una empresa.

Esta aplicacion tiene como finalidad revisar el flujo de correos dentro de una empresa haciendo un proceso simple: usando cuentas externas e internas a la empresa el programa envia constantemente correos desde las cuentas internas a las externas, cuando uno de los correos no llega el programa notifica al quien sea el encargado que hay un problema con los correos electronicos.

Uso del programa:

-Se utilizan cuatro cuentas de correo electronico, idealmente externas a la empresa debido a que las cuentas externas son las que normalmente hacen un recorrido completo del flujo normal de un correo electronico dentro de las empresas. Este programa esta diseñado para usar dos cuentas Yahoo y dos cuentas Gmail pero esto puede ser modificado dependiendo de las necesidades.

-Con las cuentas listas y configuradas para su uso con aplicaciones desconocidas o externas, estas se ingresan dentro del codigo del programa en los lugares que correspondan, y se deben agregar tambien dos correos empresariales, uno que reciba y envie correos a la cuenta Yahoo y el otro a la Gmail. La ubicacion de estos correos se especifica dentro del codigo.

-Los correos empresariales deben configurarse de manera que cuando recibe un correo de una cuenta este lo reenvie a la otra cuenta, esta al recibir ese correo envia un correo nuevamente a la cuenta empresarial donde esta debe reenviarla a la primera cuenta. En palabras mas sencillas el flujo esta configurado de la siguiente manera: Cuenta Yahoo 1 -> Cuenta empresa 1 -> Cuenta Yahoo 2 -> Cuenta empresa 1 -> Cuenta Yahoo 1. Lo mismo con las cuentas Gmail y la cuenta empresa 2.

-Una vez configurado todo se ejecuta el programa, se inicia y se deja corriendo sin cerrarlo y los correos seran enviados automaticamente.

NOTA: El programa esta configurado para enviar los correos fuera del horario de trabajo de oficina (De 18 hrs hasta las 8 hrs), esto puede ser modificado.
