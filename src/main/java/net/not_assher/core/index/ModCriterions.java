package net.not_assher.core.index;

import net.acoyt.acornlib.api.registrants.CriterionRegistrant;
import net.minecraft.advancement.criterion.TickCriterion;
import net.not_assher.core.Quartermaster;

public interface ModCriterions {
    CriterionRegistrant rant = new CriterionRegistrant(Quartermaster.MOD_ID);

    TickCriterion RAPIER = rant.register("rapier", new TickCriterion());

    static void init() {}
}
