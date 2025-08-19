package it.italiandudes.webtrpg.dnd5e.data.sheet.item;

import it.italiandudes.webtrpg.core.data.MimeImage;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5EArmorSlot;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5ERarity;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5EWearableWeightCategory;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_armors")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EArmor extends DND5EEquipment {

    // Armor Data
    @Column(name = "slot", nullable = false, columnDefinition = "NOT NULL DEFAULT 'FULL_SET'") @Enumerated(EnumType.STRING) private DND5EArmorSlot slot = DND5EArmorSlot.FULL_SET;
    @Column(name = "weight_category", nullable = false, columnDefinition = "NOT NULL DEFAULT 'LIGHT'") @Enumerated(EnumType.STRING) private DND5EWearableWeightCategory weightCategory = DND5EWearableWeightCategory.LIGHT;

    // Constructors
    public DND5EArmor(
            String name, DND5ESheet sheet, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped,
            DND5EArmorSlot slot, DND5EWearableWeightCategory weightCategory
    ) {
        super(name, sheet, itemImage, rarity, weight, quantity, costMR, description, DND5EEquipmentCategory.ARMOR, caEffect, lifeEffect, loadEffect, lifeEffectPercentage, loadEffectPercentage, otherEffects, isEquipped);
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.slot = slot != null ? slot : DND5EArmorSlot.FULL_SET;
        this.weightCategory = weightCategory != null ? weightCategory : DND5EWearableWeightCategory.LIGHT;
    }
}
