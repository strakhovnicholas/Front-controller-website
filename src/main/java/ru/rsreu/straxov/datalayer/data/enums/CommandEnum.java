package ru.rsreu.straxov.datalayer.data.enums;

import ru.rsreu.straxov.datalayer.data.admincommands.AddUserCommand;
import ru.rsreu.straxov.datalayer.data.admincommands.DeleteUserCommand;
import ru.rsreu.straxov.datalayer.data.admincommands.ShowAllUsersAdminCommand;
import ru.rsreu.straxov.datalayer.data.admincommands.UpdateUserInformationCommand;
import ru.rsreu.straxov.datalayer.data.moderatorcommands.*;
import ru.rsreu.straxov.datalayer.data.usercommands.*;
import ru.rsreu.straxov.datalayer.data.common.LoginCommand;
import ru.rsreu.straxov.datalayer.data.common.LogoutCommand;
import ru.rsreu.straxov.datalayer.data.system.ActionCommand;


public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    ADDLOT
    {
        {
            this.command = new AddLotCommand();
        }
    },
    SHOWUSERPOSTEDLOTS
    {
        {
            this.command = new ShowUserPostedLotsCommand();
        }

    },
    REMOVELOT
    {
        {
            this.command = new RemoveLotCommand();
        }

    },
    EDITLOT
    {
        {
            this.command = new UpdatePostedLotCommand();
        }
    },
    SHOWLOTSFORPURCHASE
    {
        {
            this.command = new ShowLotsForPurchaseCommand();
        }
    },
    SHOWLOTINFO
    {
        {
            this.command = new ShowLotInformationCommand();
        }
    },
    BET
    {
        {
            this.command = new BetCommand();
        }
    },
    ADDUSER
    {
        {
            this.command = new AddUserCommand();
        }
    },
    SHOWUSERS
    {
        {
            this.command = new ShowAllUsersAdminCommand();
        }
    },
    EDITUSER
    {
        {
            this.command = new UpdateUserInformationCommand();
        }
    },
    DELETEUSER
    {
        {
            this.command = new DeleteUserCommand();
        }
    },
    SHOWUSERSMODER
    {
        {
            this.command = new ShowAllUsersModeratorCommand();
        }
    },
    BLOCKUSER
    {
        {
            this.command = new BlockUserCommand();
        }
    },
    SHOWLOTSMODER
    {
        {
            this.command = new ShowAllLotsModeratorCommand();
        }
    },
    REMOVELOTMODER
    {
        {
            this.command = new RemoveLotModeratorCommand();
        }
    },
    SETENDDATEFORALLLOTS
    {
        {
            this.command = new SetEndDateLotsCommand();
        }
    },
    SETENDDATEFORCERTAINLOT
    {
        {
            this.command = new SetEndTimeCertainLotCommand();
        }
    },
    SHOWUSERREQUEST
    {
        {
            this.command = new ShowAllRequestsCommand();
        }
    },
    SETSTATUSFORLOT
    {
        {
            this.command = new SetStatusForLotCommand();
        }
    },
    SHOWEXPIREDLOTS
    {
        {
            this.command = new ShowExpiredBidsCommand();
        }
    },
    CLOSEBID
    {
        {
            this.command = new CloseBidCommand();
        }
    },
    SHOWPURCHASEDLOTSUSER
    {
        {
            this.command = new ShowPurchasedLotsCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
