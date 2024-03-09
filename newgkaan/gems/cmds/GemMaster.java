package newgkaan.gems.cmds;

import newgkaan.gems.gui.Open;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GemMaster implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Only for players!");
         return true;
      } else {
         new Open((Player)sender);
         return true;
      }
   }
}
