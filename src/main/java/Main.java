import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // Small number example
//        BigInteger n = new BigInteger("10142789312725007");

        // Video example
//        BigInteger n = new BigInteger("5261933844650100908430030083398098838688018147149529533465444719385566864605781576487305356717074882505882701585297765789323726258356035692769897420620858774763694117634408028918270394852404169072671551096321238430993811080749636806153881798472848720411673994908247486124703888115308603904735959457057925225503197625820670522050494196703154086316062123787934777520599894745147260327060174336101658295022275013051816321617046927321006322752178354002696596328204277122466231388232487691224076847557856202947748540263791767128195927179588238799470987669558119422552470505956858217654904628177286026365989987106877656917");

        BigInteger n = new BigInteger("84641628266040968134726266858994621642842114704041843715900121029779624800354442181024933538554572539172519633298583132480574427234887384864237862510536135711979678422472297405718744900411759436052738392404109005261750528594360602400820678382015390265136033037813004859176942808586218232493968301780230653856757977924165795192647059713220241170741461160395394356567033643278968175549740103490059728058303074181319743641739740188742499285217435763085176127055686256451491791548801388560202149363758975803673725819798718410301085155062206092370667133063831263112034304132340281237240943617970645337927088718346956199378919");

        int bits = n.bitLength();
        System.out.println("Number of bits: " + bits);

        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            System.out.println("2 * " + n.divide(BigInteger.valueOf(2)));
        } else {
            try {
                System.out.println("Fermat factorization of " + n + " is: ");
                BigInteger[] factors = fermatFactorization(n);
                System.out.println(factors[0] + " * " + factors[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean isPerfectSquare(BigInteger n) {
        BigInteger sqrt = n.sqrt();

        return sqrt.pow(2).equals(n);
    }

    // Method to calculate fermat factorization
    public static BigInteger[] fermatFactorization(BigInteger number) {
        BigInteger a = number.sqrt().add(BigInteger.ONE);
        System.out.println("a: " + a);
        BigInteger b = null;
        int tries = 100;

        while (tries-- > 0) {
            BigInteger b2 = a.pow(2).subtract(number);

            if (isPerfectSquare(b2)) {
                b = b2.sqrt();
                break;
            }

            a = a.add(BigInteger.ONE);
        }

        if (b == null) {
            throw new RuntimeException("Fermat factorization failed");
        }

        System.out.println("b: " + b);
        return new BigInteger[]{a.subtract(b), a.add(b)};
    }
}

