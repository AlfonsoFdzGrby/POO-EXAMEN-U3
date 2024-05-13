## POO - Examen Unidad 3
**Ing. Eder Rivera Cisneros**  

**Equipo de trabajo:**
- José Samuel Villegas Carmona
- Oscar Alejandro Arias Corona
- Alfonso Marón Fernández Garibay

---

#### Descripción:

Se realizó un sistema bancario con clientes, empleados e inversionistas, dentro del cual cada uno jugaba un papel diferente:

- **Clientes:** Los clientes van a poder ser dados de alta en el sistema únicamente por los empleados con el rol de ejecutivos de cuenta, al momento en el que se dan de alta, se les asigna una tarjeta de débito, los datos que se ocupan para dar de alta al usuario son los siguientes.

- **Empleados:**
    - Gerentes: Estas personas podrá hacer todas operaciones con las que cuente el sistema. Es importante señalar que una vez que el sistema esté listo, ya debe de contar con un gerente registrado por default.
    - Capturistas: Esta personas son las encargadas de realizar cualquier registro o modificación únicamente a los ejecutivos de cuenta. Es decir no podrá realizar ninguna otra operación que no sea sobre los ejecutivos de cuenta.
    - Ejecutivos de cuenta: Este rol es el encargado de llevar todo el proceso de los clientes. Registro, modificaciones, eliminaciones, búsquedas, etc. De igual forma, una vez que un cliente solicite una tarjeta de crédito, este rol (junto con el gerente), serán los únicos que podrán autorizarlo.

- **Inversionistas:**
    - Estas personas serán las encargadas de proveer de fondos al banco, podrán ser registrados, modificados, eliminados, etc. únicamente por el gerente, y para cualquier operación que se desee hacer con los inversionistas, el sistema tendrá que solicitar una contraseña (aún siendo el gerente), esto para garantizar la máxima seguridad en el sistema.

Además se cuenta con un sistema de tarjetas, dentro de las cuales se encuentran 2 tipos:

- **Débito:** Se trata de una cuenta base de ahorros que puede ser actualizada a una tarjeta de crédito una vez se alcance el monto mínimo requerido para hacerlo
- **Crédito:**
    - Simplicity: Mínimo de apertura de 50,000 MXN, crédito máximo de 60,000 MXN
    - Platino: Mínimo de apertura de 100,000 MXN, crédito máximo de 150,000 MXN
    - Oro: Mínimo de apertura de 200,000 MXN, crédito máximo de 400,000 MXN

Al momento en el que el cliente hace una compra con una tarjeta de crédito, restarle esa cantidad de su crédito máximo, esto para asegurar que no puede gastar más de lo que tiene permitido. Para poder gastar más con su tarjeta, debe de realizar el pago de ella para que su crédito vuelva a subir.