import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger n = new BigInteger("18784930108456612406607814982869320937100357623565293065520882099446807661280672536412900261980252211590001129415416692842708423735233445954982126337452630953642389398728359447338862517090153799904393322766932296070412642108354965913688213429893600565940214997335063029350129915211361225154608407197811535838113388016141398750885113342334046258520069354875722557429638792502313832152491728469179017286106563712702581115089781944984882457716639211444194974007013843331628459682686708234290546709545912155519705333002154730995212015948695451676666505480477641054802308304079577983185418820145281416757335093826951423117");

        // Calculate a = sqrt(n) + 1
        BigInteger a = n.sqrt().add(BigInteger.ONE);

        System.out.println("a = " + a);

        // Calculate b = a^2 - n until b is a perfect square

        BigInteger b;

        while (true) {
            BigInteger b2 = a.pow(2).subtract(n);
            // if b2 is a perfect square
            if (isPerfectSquare(b2)) {
                System.out.println("b2 = " + b2);
                b = b2.sqrt();
                break;
            } else {
                a = a.add(BigInteger.ONE);
                System.out.println("a = " + a);
            }
        }

        System.out.println("n (maybe) = " + a.multiply(b));
        System.out.println("n = " + n);
    }

    public static boolean isPerfectSquare(BigInteger n) {
        BigInteger sqrt = n.sqrt();
        return sqrt.pow(2).equals(n);
    }
}

