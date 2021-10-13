package uz.gita.findword.repository;

import java.util.ArrayList;

import uz.gita.findword.contract.MainContract;
import uz.gita.findword.questiondata.QuestionData;

public class MainRepository implements MainContract.Model {
    private ArrayList<QuestionData> data;


    public MainRepository() {
        data = new ArrayList<>();
        data.add(new QuestionData("apple", "apple", "olmasheyrisktd"));
        data.add(new QuestionData("ant", "ant", "antokxleamtsni"));
        data.add(new QuestionData("boat", "boat", "obmashetriskxd"));
        data.add(new QuestionData("basket", "basket", "absotqwkeamdqw"));
        data.add(new QuestionData("monkey", "monkey", "pcuelowakmnqoy"));
        data.add(new QuestionData("bee", "bee", "olabeioldeqmua"));
        data.add(new QuestionData("gold", "gold", "gpoemtnoablqyd"));
        data.add(new QuestionData("fork", "fork", "olaqrformatkzw"));
        data.add(new QuestionData("horse", "horse", "spamhqoznreldp"));
        data.add(new QuestionData("eraser", "eraser", "eplamsqyzremtr"));

        data.add(new QuestionData("kitten", "kitten", "linkevaetqotnw"));
        data.add(new QuestionData("nut", "nut", "lansyrotuzbqoe"));
        data.add(new QuestionData("onion", "onion", "fnonayuoeloniq"));
        data.add(new QuestionData("wolf", "wolf", "pswaqlotifhmne"));
        data.add(new QuestionData("gloves", "gloves", "pgqnpxvlsmegoa"));
        data.add(new QuestionData("flag", "flag", "qfsdlfargemapf"));
        data.add(new QuestionData("bear", "bear", "osdgbteeawqrzm"));
        data.add(new QuestionData("towel", "towel", "bwuctetchohlwr"));
        data.add(new QuestionData("axe", "axe", "agflahrxsmxeqe"));
        data.add(new QuestionData("chain", "chain", "cdmqhawiratnfn"));

        data.add(new QuestionData("torch", "torch", "buoctbtrhehqcr"));
        data.add(new QuestionData("castle", "castle", "gcsadsetwlgekg"));
        data.add(new QuestionData("cup", "cup", "pleashucrisktd"));
        data.add(new QuestionData("chief", "chief", "clmisheyfishtd"));
        data.add(new QuestionData("coal", "coal", "clmaoheyrisktd"));
        data.add(new QuestionData("earth", "earth", "rlmatheyrishtd"));
        data.add(new QuestionData("comb", "comb", "clmosheyribktd"));
        data.add(new QuestionData("fox", "fox", "flmashoyrxsktd"));
        data.add(new QuestionData("rice", "rice", "elmacheyrisktd"));
        data.add(new QuestionData("well", "well", "elmalheyriwktd"));

        data.add(new QuestionData("ring", "ring", "glmanhegrisktd"));
        data.add(new QuestionData("root", "root", "otmashoyrisktd"));
        data.add(new QuestionData("sky", "sky", "olmasheyrisktd"));
        data.add(new QuestionData("clasp", "clasp", "clmasheyrisptd"));
        data.add(new QuestionData("razor", "razor", "zrmasheyrosktd"));
        data.add(new QuestionData("wallet", "wallet", "elmawheylisktd"));
        data.add(new QuestionData("pin", "pin", "nlpasheyrisktd"));
        data.add(new QuestionData("watch", "watch", "awmawheyricktd"));
        data.add(new QuestionData("puppy", "puppy", "typmapheyruskp"));
        data.add(new QuestionData("tissue", "tissue", "tlmashesrisutd"));

        data.add(new QuestionData("lorry", "lorry", "olmrsheyrisktd"));
        data.add(new QuestionData("marker", "marker", "rlmasheyrisktd"));
        data.add(new QuestionData("plate", "plate", "olpasheyrisktd"));
        data.add(new QuestionData("puddle", "puddle", "dlpasheyrusktd"));
        data.add(new QuestionData("waiter", "waiter", "olwaiheyrisktd"));
        data.add(new QuestionData("typing", "typing", "gpmasheyrisntd"));
        data.add(new QuestionData("pea", "pea", "olmasheyriskpd"));
        data.add(new QuestionData("column", "column", "ocmlshuyrnsktd"));
        data.add(new QuestionData("hammer", "hammer", "hlmasheyrmsktd"));
       data.add(new QuestionData("chain", "chain", "nlmashcyrishtd"));
        data.add(new QuestionData("clasp", "clasp", "clmasheyrisptd"));

        data.add(new QuestionData("check", "check", "klcasheyrisctd"));
        data.add(new QuestionData("crown", "crown", "olcashewrisktn"));
        data.add(new QuestionData("ferry", "ferry", "olrasfeyrisktd"));
        data.add(new QuestionData("beads", "beads", "slmashebrisktd"));
        data.add(new QuestionData("stick", "stick", "clmasheyrisktd"));
        data.add(new QuestionData("fir", "fir", "flmasheyrisktd"));
        data.add(new QuestionData("needle", "needle", "elnasheyrisetd"));
        data.add(new QuestionData("thorn", "thorn", "hlnasheyrosktd"));
        data.add(new QuestionData("clip", "clip", "plmcsheyrisktd"));
        data.add(new QuestionData("rope", "rope", "plmosheyrisktd"));


    }


    @Override
    public int getQuestionCount() {
        return data.size();
    }

    @Override
    public QuestionData getQuestionByIndex(int index) {
        return data.get(index);
    }
}

