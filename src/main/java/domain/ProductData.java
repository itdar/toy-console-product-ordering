package domain;

import java.util.ArrayList;
import java.util.List;

public class ProductData {
    private String productData = "" +
            "768848,[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종,21000,45\n" +
            "748943,디오디너리 데일리 세트 (Daily set),19000,89\n" +
            "779989,버드와이저 HOME DJing 굿즈 세트,35000,43\n" +
            "779943,Fabrik Pottery Flat Cup & Saucer - Mint,24900,89\n" +
            "768110,네페라 손 세정제 대용량 500ml 이더블유지,7000,79\n" +
            "517643,에어팟프로 AirPods PRO 블루투스 이어폰(MWP22KH/A),260800,26\n" +
            "706803,ZEROVITY™ Flip Flop Cream 2.0 (Z-FF-CRAJ-),38000,81\n" +
            "759928,마스크 스트랩 분실방지 오염방지 목걸이,2800,85\n" +
            "213341,20SS 오픈 카라/투 버튼 피케 티셔츠 (6color),33250,99\n" +
            "377169,[29Edition.]_[스페셜구성] 뉴코튼베이직 브라렛 세트 (브라1+팬티2),24900,60\n" +
            "744775,SHUT UP [TK00112],28000,35\n" +
            "779049,\"[리퍼브/키친마켓] Fabrik Pottery Cup Saucer (단품)\",10000,64\n" +
            "611019,플루크 new 피그먼트 오버핏 반팔티셔츠 FST701 / 7color M,19800,7\n" +
            "628066,무설탕 프로틴 초콜릿 틴볼스,12900,8\n" +
            "502480,[29Edition.]_[스페셜구성] 렉시 브라렛 세트(브라1+팬티2),24900,41\n" +
            "782858,폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color),39500,50\n" +
            "760709,파버카스텔 연필1자루,200,70\n" +
            "778422,캠핑덕 우드롤테이블,45000,7\n" +
            "648418,BS 02-2A DAYPACK 26 (BLACK),238000,5";

    private Products products;

    public Products loadAll() {
        String[] splitNewlineStrings = productData.split("\n");

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < splitNewlineStrings.length; ++i) {
            String[] productElements = splitNewlineStrings[i].split(",");
            products.add(createProductUsing(productElements));
        }

        this.products = new Products(products);

        return this.products;
    }

    private Product createProductUsing(String[] productElements) {
        return new Product(
                Integer.parseInt(productElements[0]),
                productElements[1],
                Integer.parseInt(productElements[2]),
                Integer.parseInt(productElements[3])
        );
    }

    public static final String TEST = "768848\t[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종\t21000\t45\n" +
            "748943\t디오디너리 데일리 세트 (Daily set)\t19000\t89\n" +
            "779989\t버드와이저 HOME DJing 굿즈 세트\t35000\t43\n" +
            "779943\tFabrik Pottery Flat Cup & Saucer - Mint\t24900\t89\n" +
            "768110\t네페라 손 세정제 대용량 500ml 이더블유지\t7000\t79\n" +
            "517643\t에어팟프로 AirPods PRO 블루투스 이어폰(MWP22KH/A)\t260800\t26\n" +
            "706803\tZEROVITY™ Flip Flop Cream 2.0 (Z-FF-CRAJ-)\t38000\t81\n" +
            "759928\t마스크 스트랩 분실방지 오염방지 목걸이\t2800\t85\n" +
            "213341\t20SS 오픈 카라/투 버튼 피케 티셔츠 (6color)\t33250\t99\n" +
            "377169\t[29Edition.]_[스페셜구성] 뉴코튼베이직 브라렛 세트 (브라1+팬티2)\t24900\t60\n" +
            "744775\tSHUT UP [TK00112]\t28000\t35\n" +
            "779049\t\"[리퍼브/키친마켓] Fabrik Pottery Cup Saucer (단품)\"\t10000\t64\n" +
            "611019\t플루크 new 피그먼트 오버핏 반팔티셔츠 FST701 / 7color M\t19800\t7\n" +
            "628066\t무설탕 프로틴 초콜릿 틴볼스\t12900\t8\n" +
            "502480\t[29Edition.]_[스페셜구성] 렉시 브라렛 세트(브라1+팬티2)\t24900\t41\n" +
            "782858\t폴로 랄프로렌 남성 수영복반바지 컬렉션 (51color)\t39500\t50\n" +
            "760709\t파버카스텔 연필1자루\t200\t70\n" +
            "778422\t캠핑덕 우드롤테이블\t45000\t7\n" +
            "648418\tBS 02-2A DAYPACK 26 (BLACK)\t238000\t5\n" +
            "779989\t버드와이저 HOME DJing 굿즈 세트\t35000\t43\n" +
            "779989\t버드와이저 HOME DJing 굿즈 세트\t35000\t43\n" +
            "779943\tFabrik Pottery Flat Cup & Saucer - Mint\t24900\t89\n" +
            "517643\t에어팟프로 AirPods PRO 블루투스 이어폰(MWP22KH/A)\t260800\t26";

}
