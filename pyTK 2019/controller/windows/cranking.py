#------------------------------------------------------------------------------
# cranking module
#   -- contains the functions and classes for controlling the ranking window
#------------------------------------------------------------------------------
from tkinter import *

from model import calculate.*
print(dir(model))
print(model.__path__)

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
                    "Rocket Top Cargo",
                    "Rocket Top Hatch",
                    "Rocket Middle Cargo",
                    "Rocket Middle Hatch",
                    "Rocket Bottom Cargo",
                    "Rocket Bottom Hatch",
                    "Cargo Ship Cargo",
                    "Cargo Ship Hatch"]

    rankingIndex = [("None", get_none),
                    ("Rocket Top Cargo", calculate.get_rocketTopC_rank),
                    ("Rocket Top Hatch", get_rocketTopH_rank),
                    ("Rocket Middle Cargo", get_rocketMiddleC_rank),
                    ("Rocket Middle Hatch", get_rocketMiddleH_rank),
                    ("Rocket Bottom Cargo", get_rocketBottomC_rank),
                    ("Rocket Bottom Hatch", get_rocketBottomH_rank),
                    ("Cargo Ship Cargo", get_cargoShipC_rank),
                    ("Cargo Ship Hatch", get_cargoShipH_rank)]

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
