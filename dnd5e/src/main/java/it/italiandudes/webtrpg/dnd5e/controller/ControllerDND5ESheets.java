package it.italiandudes.webtrpg.dnd5e.controller;

import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.dnd5e.data.DND5ECampaign;
import it.italiandudes.webtrpg.dnd5e.data.sheet.DND5ESheet;
import it.italiandudes.webtrpg.dnd5e.repository.DND5ECampaignRepository;
import it.italiandudes.webtrpg.dnd5e.repository.DND5ESheetRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public final class ControllerDND5ESheets {

    // Repositories
    @NotNull private final DND5ECampaignRepository campaignRepository;
    @NotNull private final DND5ESheetRepository sheetRepository;

    // Constructors
    public ControllerDND5ESheets(@NotNull final DND5ECampaignRepository campaignRepository, @NotNull final DND5ESheetRepository sheetRepository) {
        this.campaignRepository = campaignRepository;
        this.sheetRepository = sheetRepository;
    }

    // Mappings
    @GetMapping("/dnd5e/campaigns/{campaignID}/sheets")
    private String sheets(@PathVariable(name = "campaignID") Long campaignID, @AuthenticationPrincipal WebTRPGUserDetails userDetails, Model model) {
        Optional<DND5ECampaign> optCampaign = campaignRepository.findById(campaignID);
        optCampaign.ifPresent(campaign -> {
            model.addAttribute("campaign", campaign);
            model.addAttribute("sheets", sheetRepository.findAllByCampaignAndOwner(campaign, userDetails.getUser()));
        });
        return "dnd5e/sheets-list";
    }

    @GetMapping("/dnd5e/campaigns/{campaignID}/sheets/{sheetID}")
    private String selectedSheet(@PathVariable(name = "campaignID") Long campaignID, @PathVariable(name = "sheetID") Long sheetID, Model model) {
        Optional<DND5ECampaign> optCampaign = campaignRepository.findById(campaignID);
        if (optCampaign.isPresent()) {
            model.addAttribute("campaign", optCampaign.get());
            Optional<DND5ESheet> optSheet = sheetRepository.findById(sheetID);
            optSheet.ifPresent(sheet -> model.addAttribute("sheet", sheet));
        }
        return "dnd5e/sheet";
    }
}
