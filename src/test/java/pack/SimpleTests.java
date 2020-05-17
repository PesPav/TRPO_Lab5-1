package pack;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleTests {

    @DataProvider(name = "FirstModuleData")
    public Object[][] createData1()
    {
        double max = Integer.MAX_VALUE - 1;
        double min = Integer.MIN_VALUE + 1;
        int n = (int)(Math.random() * max);
        int m = (int)(Math.random() * min);
        int s = n + m;
        return new Object[][]
        {
            {s}
        };
    }

    @DataProvider(name = "SecondModule1Data")
    public Object[][] createData2()
    {
        double max = Integer.MAX_VALUE - 1;
        double min = Integer.MIN_VALUE + 1;
        int n = (int)(Math.random() * max);
        int m = (int)(Math.random() * min);
        return new Object[][]
        {
            {n, m}
        };
    }

    @DataProvider(name = "SecondModule2Data")
    public Object[][] createData3()
    {
        double max = Integer.MAX_VALUE - 1;
        int n = 1 + (int)(Math.random() * max) + 1;
        return new Object[][]
        {
            {n}
        };
    }

    @DataProvider(name = "SecondModule3Data")
    public Object[][] createData4()
    {
        double min = Integer.MIN_VALUE + 1;
        int n =(int)(Math.random() * min) - 1;
        return new Object[][]
                {
                        {n}
                };
    }

    @DataProvider(name = "ThirdModule1Data")
    public Object[][] createData5()
    {
        double max = Integer.MAX_VALUE - 1;
        int n = 1 + (int)(Math.random() * max) + 1;
        int m = 1 + (int)(Math.random() * max) + 1;
        int result = n/m;
        return new Object[][]
        {
            {n, m, result}
        };
    }


    @Test(dataProvider = "FirstModuleData")
    public void FirstModule(int n)
    {
        System.out.println("Тест 1. Проверка функции Math.abs(n) корректными значениями");
        //Позитивная проверка с нормальными условиями
        // модуля Math.abs(int x)
        int m = Math.abs(n);
        System.out.println("n = " + n);
        System.out.println("Модуль n = " + m);
        if (n < 0) Assert.assertEquals(m,n * -1);
        else Assert.assertEquals(m, n);
        System.out.println();
    }

    @Test(dataProvider = "SecondModule1Data")
    public void SecondModule1(int n, int m)
    {
        System.out.println("Тест 2. Проверка функции Math.addExact корректными значениями");
        //Позитивная проверка с нормальными условиями
        // модуля Math.addExact(int x, int y)
        // чтобы использовались различные цифры,
        // выполнена функция получения случайных чисел
        // на отрезке от MAX_VALUE до MIN_VALUE
        int sum = Math.addExact(n,m);
        Assert.assertEquals(sum,n + m);
        System.out.println("Значение с помощью addExact = " + sum);
        System.out.println("Проверочное значение = " + (n + m));
        System.out.println();
    }

    @Test(dataProvider = "SecondModule2Data")
    public void SecondModule2(int m)
    {
        System.out.println("Тест 3. Проверка функции Math.addExact переполнением с положительными числами");

        //Проверка модуля Math.addExact(int x, int y)
        // на переполнение типа int с положительными числами
        // MAX_VALUE - максимальное значение для типа int
        // При попытке выполнения должна возникать ошибка
        // типа RuntimeException, содержащая информацию о
        // переполнении
        int n = Integer.MAX_VALUE;
        try {
            int sum = Math.addExact(n,m);
        }
        catch (RuntimeException e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: integer overflow");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }

    @Test(dataProvider = "SecondModule3Data")
    public void SecondModule3(int m)
    {
        System.out.println("Тест 4. Проверка функции Math.addExact переполнением с отрицательными числами");

        //Проверка модуля Math.addExact(int x, int y)
        // на переполнение типа int с отрицательными числами
        // MIN_VALUE - минимальное значение для типа int
        // При попытке выполнения должна возникать ошибка
        // типа RuntimeException, содержащая информацию о
        // переполнении
        int n = Integer.MIN_VALUE;
        try {
            int sum = Math.addExact(n,m);
        }
        catch (RuntimeException e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: integer overflow");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }

    @Test(dataProvider = "ThirdModule1Data")
    public void ThirdModule1(int n, int m, int res)
    {
        System.out.println("Тест 5. Проверка функции Math.floorDiv c корректными значениями");

        int result = Math.floorDiv(n, m);
        System.out.println("Первое число = " + n + "; Второе число = " + m);
        System.out.println("Результат floorDiv: " + result);
        System.out.println("Проверка: " + res);
        Assert.assertEquals(result, res);
        System.out.println();
    }

    @Test(dataProvider = "FirstModuleData")
    public void ThirdModule2(int n)
    {
        System.out.println("Тест 5. Проверка функции Math.floorDiv c делением на ноль");

        int m = 0;
        try {
            Math.floorDiv(n,m);
        }
        catch (Throwable e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: / by zero");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: / by zero");
        }
        System.out.println();

    }

}
