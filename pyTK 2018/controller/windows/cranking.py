#------------------------------------------------------------------------------
# cranking module
#   -- contains the functions and classes for controlling the ranking window
#------------------------------------------------------------------------------
from Tkinter import *

from model.calculate import *

#------------------------------------------------------------------------------
# get_none function
#   -- passes through so that a None list can delete all of its contents
#------------------------------------------------------------------------------
def get_none(sort=None,rev=None):
    pass

#------------------------------------------------------------------------------
# RankingController Class
#   -- used to get and set data for Ranking objects and their Listings
#------------------------------------------------------------------------------
class RankingController():

    # use these to index values to display, use the system: ("key",term)
    # where key corredsponds to a value in self.rankingTypes and term is
    # the function to call when that option is selected
    rankingTypes = ["None",
                    "Auto Cubes In Opposing Switch",
                    "Auto Cubes In Alliance Switch",
                    "Auto Cubes In Scale",
                    "Auto Cubes In Exchange",
                    "Auto Time It Takes To Gain Ownership In Opposing Switch",
                    "Auto Time It Takes To Gain Ownership In Alliance Switch",
                    "Auto Time It Takes To Gain Ownership In Scale",
                    "Auto Time Owning Opposing Switch",
                    "Auto Time Owning Alliance Switch",
                    "Auto Time Owning Scale",
                    "Tele Cubes In Opposing Switch",
                    "Tele Cubes In Alliance Switch",
                    "Tele Cubes In Scale",
                    "Tele Cubes In Exchange",
                    "Tele Time It Takes To Gain Ownership In Opposing Switch",
                    "Tele Time It Takes To Gain Ownership In Alliance Switch",
                    "Tele Time It Takes To Gain Ownership In Scale",
                    "Tele Time Owning Opposing Switch",
                    "Tele Time Owning Alliance Switch",
                    "Tele Time Owning Scale",
                    #"Cubes In Vault",
                    "Cubes When Force Is Activated",
                    "Cubes When Levitate Is Activated",
                    "Cubes When Boost Is Activated"]

    rankingIndex = [("None", get_none),
                    ("Auto Cubes In Opposing Switch", get_autoCubesInSw1_rank),
                    ("Auto Cubes In Alliance Switch", get_autoCubesInSw2_rank),
                    ("Auto Cubes In Scale", get_autoCubesInScl_rank),
                    ("Auto Cubes In Exchange", get_autoCubesInEx_rank),
                    ("Auto Time It Takes To Gain Ownership In Opposing Switch", get_autoOwnGainSw1_rank),
                    ("Auto Time It Takes To Gain Ownership In Alliance Switch", get_autoOwnGainSw2_rank),
                    ("Auto Time It Takes To Gain Ownership In Scale", get_autoOwnGainScl_rank),
                    ("Auto Time Owning Opposing Switch", get_autoOwnedSw1_rank),
                    ("Auto Time Owning Alliance Switch", get_autoOwnedSw2_rank),
                    ("Auto Time Owning Scale", get_autoOwnedScl_rank),
                    ("Tele Cubes In Opposing Switch", get_teleCubesInSw1_rank),
                    ("Tele Cubes In Alliance Switch", get_teleCubesInSw2_rank),
                    ("Tele Cubes In Scale", get_teleCubesInScl_rank),
                    ("Tele Cubes In Exchange", get_teleCubesInEx_rank),
                    ("Tele Time It Takes To Gain Ownership In Opposing Switch", get_teleOwnGainSw1_rank),
                    ("Tele Time It Takes To Gain Ownership In Alliance Switch", get_teleOwnGainSw2_rank),
                    ("Tele Time It Takes To Gain Ownership In Scale", get_teleOwnGainScl_rank),
                    ("Tele Time Owning Opposing Switch", get_teleOwnedSw1_rank),
                    ("Tele Time Owning Alliance Switch", get_teleOwnedSw2_rank),
                    ("Tele Time Owning Scale", get_teleOwnedScl_rank),
                    ("Cubes in Vault",get_cubesInVault_rank),
                    #("Cubes When Force Is Activated", get_cubesAtActiveFce_rank),
                    ("Cubes When Levitate Is Activated", get_cubesAtActiveLev_rank),
                    ("Cubes When Boost Is Activated", get_cubesAtActiveBst_rank)]

    sortOptions = [("Maximum","max"),("Average","avg"),("Minimum","min")]

    def __init__(self):
        self.sort = StringVar()
        self.sort.set("avg")
        self.rev = BooleanVar()
        self.rev.set(1)
        self.data = None

    def load_rankings(self,kind="None"):
        for value, func in self.rankingIndex:
            if value==kind:
                self.data = func(sort=self.sort.get(),rev=self.rev.get())
                break
