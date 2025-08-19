package it.italiandudes.webtrpg.dnd5e.controller;

import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.dnd5e.data.DND5ECampaign;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.*;
import it.italiandudes.webtrpg.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webtrpg.dnd5e.data.sheet.item.*;
import it.italiandudes.webtrpg.dnd5e.data.sheet.proficiency.DND5EWeaponProficiency;
import it.italiandudes.webtrpg.dnd5e.repository.DND5ECampaignRepository;
import it.italiandudes.webtrpg.dnd5e.repository.DND5EItemRepository;
import it.italiandudes.webtrpg.dnd5e.repository.DND5ESheetRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public final class ControllerDND5ESheets {

    // Repositories
    @NotNull private final DND5ECampaignRepository campaignRepository;
    @NotNull private final DND5ESheetRepository sheetRepository;
    private final DND5EItemRepository itemRepository;

    // Constructors
    public ControllerDND5ESheets(@NotNull final DND5ECampaignRepository campaignRepository, @NotNull final DND5ESheetRepository sheetRepository,
                                 DND5EItemRepository itemRepository) {
        this.campaignRepository = campaignRepository;
        this.sheetRepository = sheetRepository;
        this.itemRepository = itemRepository;
    }

    // Mappings
    @GetMapping("/dnd5e/campaigns/{campaign-id}/sheets")
    private String sheets(@PathVariable(name = "campaign-id") Long campaignID, @AuthenticationPrincipal WebTRPGUserDetails userDetails, Model model) {
        Optional<DND5ECampaign> optCampaign = campaignRepository.findById(campaignID);
        optCampaign.ifPresent(campaign -> {
            model.addAttribute("campaign", campaign);

            DND5ESheet sheet = DND5ESheet.builder().campaign(campaign).owner(userDetails.getUser()).build();
            sheet.getTabProficiencies().getWeapons().add(new DND5EWeaponProficiency("TESTER SPADA", DND5EProficiencyLevel.PROFICIENCY));
            sheet.getTabProficiencies().getWeapons().add(new DND5EWeaponProficiency("TESTER SPADA PT2", DND5EProficiencyLevel.MASTERY));
            sheetRepository.save(sheet);

            /*
            DND5EItem item = new DND5EItem("TESTER ITEM", sheet, DND5EItemCategory.ITEM, null, DND5ERarity.EXOTIC, 1.5, 1, 1, "Tester Description");
            DND5EArmor armor = new DND5EArmor("TESTER ARMOR", sheet, null, DND5ERarity.EXOTIC, 56.1, 5, 12, "TESTER DESC", 1, 8, 5, 8.64, 3.5, "TESTER BELLO", true, DND5EArmorSlot.CHEST, DND5EWearableWeightCategory.HEAVY);
            DND5EDress dress = new DND5EDress("TESTER DRESS", sheet, null, DND5ERarity.EXOTIC, 56.1, 5, 12, "TESTER DESC", 1, 8, 5, 8.64, 3.5, "TESTER BELLO", true, DND5EWearableWeightCategory.HEAVY);
            DND5EWeapon weapon = new DND5EWeapon("TESTER WEAPON", sheet, null, DND5ERarity.EXOTIC, 56.1, 5, 12, "TESTER DESC", 1, 8, 5, 8.64, 3.5, "TESTER BELLO", true, "Spadone", "PROP BELLE");
            DND5EAddon addon = new DND5EAddon("TESTER ADDON", sheet, null, DND5ERarity.EXOTIC, 56.1, 5, 12, "TESTER DESC", 1, 8, 5, 8.64, 3.5, "TESTER BELLO", true, DND5EAddonSlot.BRACELET);

            itemRepository.save(item);
            itemRepository.save(armor);
            itemRepository.save(dress);
            itemRepository.save(weapon);
            itemRepository.save(addon);*/

            List<DND5ESheet> sheets = sheetRepository.findAllByCampaignAndOwner(campaign, userDetails.getUser());
            model.addAttribute("sheets", sheets);
        });
        return "dnd5e/sheets-list";
    }

    @GetMapping("/dnd5e/campaigns/{campaign-id}/sheets/{sheet-id}")
    private String selectedSheet(@PathVariable(name = "campaign-id") Long campaignID, @PathVariable(name = "sheet-id") Long sheetID, Model model) {
        Optional<DND5ECampaign> optCampaign = campaignRepository.findById(campaignID);
        if (optCampaign.isPresent()) {
            model.addAttribute("campaign", optCampaign.get());
            Optional<DND5ESheet> optSheet = sheetRepository.findById(sheetID);
            optSheet.ifPresent(sheet -> {
                System.err.println(sheet.getTabProficiencies().getWeapons());
                model.addAttribute("sheet", sheet);
            });
        }
        return "dnd5e/sheet";
    }
}
