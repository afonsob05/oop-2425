package hva.app.main;

import hva.core.HotelManager;
import hva.core.Season;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
  }

  @Override
  protected final void execute() {
    _receiver.advanceSeason();
    Season currentSeason = _receiver.getCurrentSeason();
    int seasonCode = currentSeason.getSeasonCode();
    _display.popup(seasonCode);
  }
}
