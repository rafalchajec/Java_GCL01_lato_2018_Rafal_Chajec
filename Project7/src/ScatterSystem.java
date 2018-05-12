public class ScatterSystem
{
    double makeOperation(double[] tab)
    {
       double value=1;

       for(double x : tab)
       {
           value=x+value;
       }

       return value;
    }
}
