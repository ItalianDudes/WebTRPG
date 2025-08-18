package it.italiandudes.webtrpg.dnd5e.data.sheet.item;

import it.italiandudes.webtrpg.core.data.MimeImage;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5EEquipmentCategory;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5ERarity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_weapons")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public class DND5EWeapon extends DND5EEquipment {

    // Entity ID
    // @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Armor Data
    @Column(name = "weapon_category", nullable = false) private String weaponCategory = "";
    @Column(name = "properties", nullable = false) private String properties = "";

    // Constructors
    public DND5EWeapon(
            String name, MimeImage itemImage, DND5ERarity rarity, Double weight, Integer quantity, Integer costMR, String description,
            Integer caEffect, Integer lifeEffect, Integer loadEffect, Double lifeEffectPercentage, Double loadEffectPercentage, String otherEffects, Boolean isEquipped,
            String weaponCategory, String properties
    ) {
        super(name, itemImage, rarity, weight, quantity, costMR, description, DND5EEquipmentCategory.WEAPON, caEffect, lifeEffect, loadEffect, lifeEffectPercentage, loadEffectPercentage, otherEffects, isEquipped);
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.weaponCategory = weaponCategory != null ? weaponCategory : "";
        this.properties = properties != null ? properties : "";
    }
}
