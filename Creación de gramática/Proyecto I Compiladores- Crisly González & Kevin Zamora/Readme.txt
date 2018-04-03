Es importante tomar en cuenta que para visualizar el arbol no deben existir errores en el código prueba ingresado

Para Visualizar el Arbol:

class otra {
  public static void main(String[] a) {
    Print( this.operaciones(arreglo[2],5));
  }
}

Para visualizar Errores:

class otra {
  public static void main(String[] a) {
    Print( this.operaciones(arreglo[2],5));
  }
}

class otra1{
  int[] arreglo;

  public int operaciones(int op1, int op2)
  {
    int i;
    int result;

    i = 10;
    arreglo = new int[MAX];

    while (i < 0)
      arreglo[i] = i;

    if ((op1 < 1) && (op2 < 0))
    {
      result = (op1 + arreglo[2]) * (op2 - arreglo[5]);
    }
    else
    {
      result = arreglo.length;
    }


    return result;
  }
}
