package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.dnd5e.data.sheet.item.DND5EAddon;
import it.italiandudes.webtrpg.dnd5e.data.sheet.item.DND5EArmor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_equipments")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabEquipment extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Armors
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "full_set_id", referencedColumnName = "id") private DND5EArmor fullSet = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "head_id", referencedColumnName = "id") private DND5EArmor head = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_shoulder_id", referencedColumnName = "id") private DND5EArmor leftShoulder = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_shoulder_id", referencedColumnName = "id") private DND5EArmor rightShoulder = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_arm_id", referencedColumnName = "id") private DND5EArmor leftArm = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_arm_id", referencedColumnName = "id") private DND5EArmor rightArm = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_forearm_id", referencedColumnName = "id") private DND5EArmor leftForearm = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_forearm_id", referencedColumnName = "id") private DND5EArmor rightForearm = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_hand_id", referencedColumnName = "id") private DND5EArmor leftHand = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_hand_id", referencedColumnName = "id") private DND5EArmor rightHand = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chest_id", referencedColumnName = "id") private DND5EArmor chest = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "back_id", referencedColumnName = "id") private DND5EArmor back = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "abdomen_id", referencedColumnName = "id") private DND5EArmor abdomen = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_leg_id", referencedColumnName = "id") private DND5EArmor leftLeg = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_leg_id", referencedColumnName = "id") private DND5EArmor rightLeg = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_knee_id", referencedColumnName = "id") private DND5EArmor leftKnee = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_knee_id", referencedColumnName = "id") private DND5EArmor rightKnee = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_foot_id", referencedColumnName = "id") private DND5EArmor leftFoot = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_foot_id", referencedColumnName = "id") private DND5EArmor rightFoot = null;

    // Addons
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "necklace_id", referencedColumnName = "id") private DND5EAddon necklace = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "mantle_id", referencedColumnName = "id") private DND5EAddon mantle = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "backpack_id", referencedColumnName = "id") private DND5EAddon backpack = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "bandolier_id", referencedColumnName = "id") private DND5EAddon bandolier = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "belt_id", referencedColumnName = "id") private DND5EAddon belt = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_earring_id", referencedColumnName = "id") private DND5EAddon leftEarring = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_earring_id", referencedColumnName = "id") private DND5EAddon rightEarring = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "left_bracelet_id", referencedColumnName = "id") private DND5EAddon leftBracelet = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "right_bracelet_id", referencedColumnName = "id") private DND5EAddon rightBracelet = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ring1_id", referencedColumnName = "id") private DND5EAddon ring1 = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ring2_id", referencedColumnName = "id") private DND5EAddon ring2 = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ring3_id", referencedColumnName = "id") private DND5EAddon ring3 = null;
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ring4_id", referencedColumnName = "id") private DND5EAddon ring4 = null;

    // Constructors
    @Builder
    public DND5ESheetTabEquipment(
            DND5EArmor fullSet, DND5EArmor head, DND5EArmor leftShoulder, DND5EArmor rightShoulder,
            DND5EArmor leftArm, DND5EArmor rightArm, DND5EArmor leftForearm, DND5EArmor rightForearm,
            DND5EArmor leftHand, DND5EArmor rightHand, DND5EArmor chest, DND5EArmor back, DND5EArmor abdomen,
            DND5EArmor leftLeg, DND5EArmor rightLeg, DND5EArmor leftKnee, DND5EArmor rightKnee,
            DND5EArmor leftFoot, DND5EArmor rightFoot, DND5EAddon necklace, DND5EAddon mantle,
            DND5EAddon backpack, DND5EAddon bandolier, DND5EAddon belt, DND5EAddon leftEarring,
            DND5EAddon rightEarring, DND5EAddon leftBracelet, DND5EAddon rightBracelet,
            DND5EAddon ring1, DND5EAddon ring2, DND5EAddon ring3, DND5EAddon ring4
    ) {
        this.fullSet = fullSet;
        this.head = head;
        this.leftShoulder = leftShoulder;
        this.rightShoulder = rightShoulder;
        this.leftArm = leftArm;
        this.rightArm = rightArm;
        this.leftForearm = leftForearm;
        this.rightForearm = rightForearm;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.chest = chest;
        this.back = back;
        this.abdomen = abdomen;
        this.leftLeg = leftLeg;
        this.rightLeg = rightLeg;
        this.leftKnee = leftKnee;
        this.rightKnee = rightKnee;
        this.leftFoot = leftFoot;
        this.rightFoot = rightFoot;
        this.necklace = necklace;
        this.mantle = mantle;
        this.backpack = backpack;
        this.bandolier = bandolier;
        this.belt = belt;
        this.leftEarring = leftEarring;
        this.rightEarring = rightEarring;
        this.leftBracelet = leftBracelet;
        this.rightBracelet = rightBracelet;
        this.ring1 = ring1;
        this.ring2 = ring2;
        this.ring3 = ring3;
        this.ring4 = ring4;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabEquipment that = (DND5ESheetTabEquipment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
