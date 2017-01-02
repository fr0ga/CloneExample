import java.util.Objects;

/**
 * Created by Andrey on 02.01.2017.
 */
public class CloneExample implements Cloneable {

    int i;
    int[] a;
    String s;

    CloneExample() {
        i = 0;
        a = new int[]{0, 0, 0};
        s = "";
    }

    CloneExample(int i, int[] a, String s) {
        this.i = i;
        this.a = a;
        this.s = s;
    }

    public boolean equals(Object otherObject)
    {
        // a quick test to see if the objects are identical
        if (this == otherObject) return true;

        // must return false if the explicit parameter is null
        if (otherObject == null) return false;

        // if the classes don't match, they can't be equal
        if (getClass() != otherObject.getClass()) return false;

        // now we know otherObject is a non-null CloneExample
        CloneExample other = (CloneExample) otherObject;

        // test whether the fields have identical values
        boolean arrAreEqual = true;
        for (int j = 0; j < a.length; j++) {
            if (this.a[j] != other.a[j]) {
                arrAreEqual = false;
            }
        }
        return Objects.equals(s, other.s) && i == other.i && arrAreEqual;
    }

    // How to clone (CloneExample implements Cloneable)
    public CloneExample clone() throws CloneNotSupportedException
    {
        // call Object.clone()
        CloneExample cloned = (CloneExample) super.clone();

        // clone mutable fields
        cloned.a = new int[this.a.length];
        System.arraycopy( this.a,0, cloned.a, 0, this.a.length );

        return cloned;
    }


    // Bad Way if there are mutable fields
    CloneExample cloneByMethod(CloneExample otherCloneExample) {
        return new CloneExample(otherCloneExample.i, otherCloneExample.a, otherCloneExample.s);
    }

    // Good way
    CloneExample cloneByMethod2 (CloneExample otherCloneExample) throws CloneNotSupportedException {

        // clone mutable fields
        int arr[] = new int[this.a.length];
        System.arraycopy( this.a,0, arr, 0, this.a.length );

        return new CloneExample(otherCloneExample.i, arr, otherCloneExample.s);
    }

    @Override
    public String toString() {
        String arr = "";
        for (int element: a) {
            arr += element + " ";
        }
        return (arr + " " + i + " " + s);
    }



    public static void main(String[] args) {
        CloneExample cloneExample = new CloneExample(1, new int[]{1, 2, 3}, "s1");
        CloneExample cloneExample1 = null;

        try {
            cloneExample1 = cloneExample.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

       // cloneExample1 = cloneExample.cloneByMethod(cloneExample);

        System.out.println(cloneExample);
        System.out.println(cloneExample1);
        System.out.println("cloneExample.equals(cloneExample1) = " + cloneExample.equals(cloneExample1));

        System.out.println();

        CloneExample cloneExample2 = null;
        try {
            cloneExample2 = cloneExample.cloneByMethod2(cloneExample);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(cloneExample);
        System.out.println(cloneExample2);
        System.out.println("cloneExample.equals(cloneExample2) = " + cloneExample.equals(cloneExample2));

        System.out.println();
        cloneExample1.i = 5;
        cloneExample2.i = 5;
        System.out.println(cloneExample);
        System.out.println(cloneExample1);
        System.out.println(cloneExample2);
        System.out.println("cloneExample.equals(cloneExample1) = " + cloneExample.equals(cloneExample1));
        System.out.println("cloneExample.equals(cloneExample2) = " + cloneExample.equals(cloneExample2));
        System.out.println("cloneExample1.equals(cloneExample2) = " + cloneExample1.equals(cloneExample2));


        cloneExample1.i = 1;
        cloneExample2.i = 1;
        System.out.println();

        System.out.println();
        cloneExample1.s = "s5";
        cloneExample2.s = "s5";
        System.out.println(cloneExample);
        System.out.println(cloneExample1);
        System.out.println(cloneExample2);
        System.out.println("cloneExample.equals(cloneExample1) = " + cloneExample.equals(cloneExample1));
        System.out.println("cloneExample.equals(cloneExample2) = " + cloneExample.equals(cloneExample2));
        System.out.println("cloneExample1.equals(cloneExample2) = " + cloneExample1.equals(cloneExample2));

        cloneExample1.s = "s1";
        cloneExample2.s = "s1";
        System.out.println();

        System.out.println();
        cloneExample1.a[0] = 5;  // cloneExample.a[0] doesnt change
        //cloneExample2.a[0] = 5;
        System.out.println(cloneExample);
        System.out.println(cloneExample1);
        System.out.println(cloneExample2);
        System.out.println("cloneExample.equals(cloneExample1) = " + cloneExample.equals(cloneExample1));
        System.out.println("cloneExample.equals(cloneExample2) = " + cloneExample.equals(cloneExample2));
        System.out.println("cloneExample1.equals(cloneExample2) = " + cloneExample1.equals(cloneExample2));

        cloneExample1.a[0] = 1;
        System.out.println();

        System.out.println();
        // cloneExample1.a[0] = 5;
        cloneExample2.a[0] = 5; // cloneExample.a[0]  changes
        System.out.println(cloneExample);
        System.out.println(cloneExample1);
        System.out.println(cloneExample2);
        System.out.println("cloneExample.equals(cloneExample1) = " + cloneExample.equals(cloneExample1));
        System.out.println("cloneExample.equals(cloneExample2) = " + cloneExample.equals(cloneExample2));
        System.out.println("cloneExample1.equals(cloneExample2) = " + cloneExample1.equals(cloneExample2));

        cloneExample2.a[0] = 1;
        System.out.println();

    }



}
