import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        // Answer 3
        BigInteger n = new BigInteger("84641628266040968134726266858994621642842114704041843715900121029779624800354442181024933538554572539172519633298583132480574427234887384864237862510536135711979678422472297405718744900411759436052738392404109005261750528594360602400820678382015390265136033037813004859176942808586218232493968301780230653856757977924165795192647059713220241170741461160395394356567033643278968175549740103490059728058303074181319743641739740188742499285217435763085176127055686256451491791548801388560202149363758975803673725819798718410301085155062206092370667133063831263112034304132340281237240943617970645337927088718346956199378919");
        BigInteger e = new BigInteger("65537");
        BigInteger c = new BigInteger("76578687539749270334567433327419068016846482753554386520492865010545019207873571124020033190161215682615446396410129994711325892805940645283340607450605814292242214864807736616366963263577528008848595692050559543833562773099105599921926111989644313805176327043401089098178374013481826785604433013115807761169019821323105749924330624040032176017861852570855402207200857443405652874317191870029368327928342025351921450031345537488126081651667235334445880387925966636557003621257916083406394805099048054073995341365825171820787167114782405887040220243880710430205621846850135276025997937602400232787790214801902431955984378");



        // Get ready totient from https://www.alpertron.com.ar/ECM.HTM
        BigInteger totient = new BigInteger("84516803768443436008870582599366776540378343256927724441500766354580697849919982137159220881841107558384354428848631066492252969491314811400330505225820206347623118469534985685566631494928224688255274045817526704694220718637112760325581270453813377912401728280078290170995903595786280592959296358445896427332617271864342460431641623319336015254470110992488107856058986565660666022208338175955358201749277310400502073097409242495202228992741332374280992965426013739094653744106822915871583832317071062315132429165416999100115449330384460920117543787585610243271161191783210545169458790400000000000000000000000000000000000");

        BigInteger d = e.modInverse(totient);

        System.out.println("Message: " + decryptMessage(c, d, n));

        // Answer 4
        n = new BigInteger("18784930108456612406607814982869320937100357623565293065520882099446807661280672536412900261980252211590001129415416692842708423735233445954982126337452630953642389398728359447338862517090153799904393322766932296070412642108354965913688213429893600565940214997335063029350129915211361225154608407197811535838113388016141398750885113342334046258520069354875722557429638792502313832152491728469179017286106563712702581115089781944984882457716639211444194974007013843331628459682686708234290546709545912155519705333002154730995212015948695451676666505480477641054802308304079577983185418820145281416757335093826951423117");
        e = new BigInteger("10706997089278343179826885210060827147463409493523510777431387685771671442851399238492089215565793886478149477207867523308649570870806078530234678604755861159531961701795254771192002888524222172978592793668157761774276617760632894679477733512701268203499385472869419715289158276662873644519187935785549641679751291116387452539507235136056210643936490424871113629840892783091758193454166139128577293272832880405477781482386734589325917451384947487161071406557141600567159179305543501912418793324336259009277406706410254025135571209187854192795599940361924030053346219417234844146017500403546598659553371609984800535467");
        c = new BigInteger("15197314651067757355946584379946834164931969541043120164078064085944642365278681324096893476214152328730756513591485443945964837192597487877625274868334436921605239988927931381505891228509512463939955833545400394889094131125180282146395678844330453848518996586269025617541770111167605227113505192399546564506354156746766953521298852057983986116832842122594930311561489326839768342790373109683646472371945983378497288646413872942727101686965536906488145794402081854686784352899478311196208601541472632958619970624634811042002621463373384931741642559063446141175096440251352453487199537100563902295374105432210649258622");

        WienerAttack wienerAttack = new WienerAttack();
        d = wienerAttack.attack(e, n);

        System.out.println("Message: " + decryptMessage(c, d, n));
    }

    /**
     * Do the final calculation to get the decrypted message
     *
     * @param c
     * @param d
     * @param n
     * @return String
     */
    private static String decryptMessage(BigInteger c, BigInteger d, BigInteger n) {
        BigInteger m = c.modPow(d, n);

        System.out.println("d: " + d);
        System.out.println("m: " + m);

        return new String(m.toByteArray());
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


