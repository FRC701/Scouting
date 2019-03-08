#------------------------------------------------------------------------------
# calculate module
#   -- functions for handling data input, output, and calculations
#------------------------------------------------------------------------------
import math
#from statlib import *

from . import stats.*
from . import imports
from . import team

# import model.stats
# import model.imports
# import model.team
#------------------------------------------------------------------------------
# calculate_data function
#   -- handles data and stores it to the teams
#------------------------------------------------------------------------------
def calculate_data():
    #adds stats values to team data
    populate_teaminfo()

    # get primary offensive information about the team

    for team in Team.team_list:
        rocketTopC = (float(team.Scores.avgRocketTopC)/4)*3.0
        rocketTopH = (float(team.Scores.avgRocketTopH)/4)*2.0
        rocketMiddleC = (float(team.Scores.avgRocketMiddleC)/4)*3.0
        rocketMiddleH = (float(team.Scores.avgRocketMiddleH)/4)*2.0
        rocketBottomC = (float(team.Scores.avgRocketBottomC)/4)*3.0
        rocketBottomH = (float(team.Scores.avgRocketBottomH)/4)*2.0
        cargoShipC = (float(team.Scores.avgCargoShipC)/4)*3.0
        cargoShipH = (float(team.Scores.avgCargoShipH)/4)*2.0

        team.totalWS = str(round(float(team.totalWS)+(rocketTopC+rocketTopH+rocketMiddleC+rocketMiddleH+rocketBottomC+rocketBottomH+cargoShipC+cargoShipH), 2))
        add_teamInfo(team)
        
        
#------------------------------------------------------------------------------
# calculate_pit_data function
#   - handles pit data and stores it to the teams
#------------------------------------------------------------------------------

#TODO change to incorperate sql
def calculate_pit_data():
    for entry in PitEntry.entries:
        done = False
        for team in Team.team_list:
            if team.number == entry.team:
                assign_pit_entry_values(team, entry)
                done = True
        if done == False:
            newTeam = Team(entry.team)
            print ("Added Team #: " + str(entry.team))
            assign_pit_entry_values(Team.team_list[len(Team.team_list)-1],entry)
        
#------------------------------------------------------------------------------
# assign_pit_entry_values function
#   -- takes PitEntry values and puts them into a team
#   -- still needs error handling
#------------------------------------------------------------------------------
def assign_pit_entry_values(team, entry):
    
    team.PitInfo.answer1 = entry.answer1
    #team.PitInfo.answer2 = entry.answer2
    team.PitInfo.answer3 = entry.answer3
    team.PitInfo.answer4 = entry.answer4
    team.PitInfo.answer5 = entry.answer5
    team.PitInfo.answer6 = entry.answer6
    
    
#------------------------------------------------------------------------------
# get_rank functions
#   -- calculates rankings for avg, min, and max scores for each team
#------------------------------------------------------------------------------

def get_rocketTopC_rank(sort="avg", rev=True):
    TeamRankings.rocketTopC_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketTopC_rank.append([team.Scores.avgRocketTopC, team.number])
        elif sort == "max":
            TeamRankings.rocketTopC_rank.append([team.Scores.maxRocketTopC, team.number])
        elif sort == "min":
            TeamRankings.rocketTopC_rank.append([team.Scores.minRocketTopC, team.number])
    TeamRankings.rocketTopC_rank.sort(reverse=rev)
    return TeamRankings.rocketTopC_rank


def get_rocketTopH_rank(sort="avg", rev=True):
    TeamRankings.rocketTopH_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketTopH_rank.append([team.Scores.avgRocketTopH, team.number])
        elif sort == "max":
            TeamRankings.rocketTopH_rank.append([team.Scores.maxRocketTopH, team.number])
        elif sort == "min":
            TeamRankings.rocketTopH_rank.append([team.Scores.minRocketTopH, team.number])
    TeamRankings.rocketTopH_rank.sort(reverse=rev)
    return TeamRankings.rocketTopH_rank


def get_rocketMiddleC_rank(sort="avg", rev=True):
    TeamRankings.rocketMiddleC_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketMiddleC_rank.append([team.Scores.avgRocketMiddleC, team.number])
        elif sort == "max":
            TeamRankings.rocketMiddleC_rank.append([team.Scores.maxRocketMiddleC, team.number])
        elif sort == "min":
            TeamRankings.rocketMiddleC_rank.append([team.Scores.minRocketMiddleC, team.number])
    TeamRankings.rocketMiddleC_rank.sort(reverse=rev)
    return TeamRankings.rocketMiddleC_rank


def get_rocketMiddleH_rank(sort="avg", rev=True):
    TeamRankings.rocketMiddleH_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketMiddleH_rank.append([team.Scores.avgRocketMiddleH, team.number])
        elif sort == "max":
            TeamRankings.rocketMiddleH_rank.append([team.Scores.maxRocketMiddleH, team.number])
        elif sort == "min":
            TeamRankings.rocketMiddleH_rank.append([team.Scores.minRocketMiddleH, team.number])
    TeamRankings.rocketMiddleH_rank.sort(reverse=rev)
    return TeamRankings.rocketMiddleH_rank


def get_rocketBottomC_rank(sort="avg", rev=True):
    TeamRankings.rocketBottomC_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketBottomC_rank.append([team.Scores.avgRocketBottomC, team.number])
        elif sort == "max":
            TeamRankings.rocketBottomC_rank.append([team.Scores.maxRocketBottomC, team.number])
        elif sort == "min":
            TeamRankings.rocketBottomC_rank.append([team.Scores.minRocketBottomC, team.number])
    TeamRankings.rocketBottomC_rank.sort(reverse=rev)
    return TeamRankings.rocketBottomC_rank


def get_rocketBottomH_rank(sort="avg", rev=True):
    TeamRankings.rocketBottomH_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.rocketBottomH_rank.append([team.Scores.avgRocketBottomH, team.number])
        elif sort == "max":
            TeamRankings.rocketBottomH_rank.append([team.Scores.maxRocketBottomH, team.number])
        elif sort == "min":
            TeamRankings.rocketBottomH_rank.append([team.Scores.minRocketBottomH, team.number])
    TeamRankings.rocketBottomH_rank.sort(reverse=rev)
    return TeamRankings.rocketBottomH_rank


def get_cargoShipC_rank(sort="avg", rev=True):
    TeamRankings.cargoShipC_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cargoShipC_rank.append([team.Scores.avgCargoShipC, team.number])
        elif sort == "max":
            TeamRankings.cargoShipC_rank.append([team.Scores.maxCargoShipC, team.number])
        elif sort == "min":
            TeamRankings.cargoShipC_rank.append([team.Scores.minCargoShipC, team.number])
    TeamRankings.cargoShipC_rank.sort(reverse=rev)
    return TeamRankings.cargoShipC_rank


def get_cargoShipH_rank(sort="avg", rev=True):
    TeamRankings.cargoShipH_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cargoShipH_rank.append([team.Scores.avgCargoShipH, team.number])
        elif sort == "max":
            TeamRankings.cargoShipH_rank.append([team.Scores.maxCargoShipH, team.number])
        elif sort == "min":
            TeamRankings.cargoShipH_rank.append([team.Scores.minCargoShipH, team.number])
    TeamRankings.cargoShipH_rank.sort(reverse=rev)
    return TeamRankings.cargoShipH_rank


#------------------------------------------------------------------------------
# predict functions
#   -- calculates predicted alliance scores predicts match outcomes
#------------------------------------------------------------------------------

#TODO put function from calculate predictions here
def predict_scores(team1=None,team2=None,team3=None):
    #pOff1 = float(team1.pOff.rstrip("%"))/100
    #pOff2 = float(team2.pOff.rstrip("%"))/100
    #pOff3 = float(team3.pOff.rstrip("%"))/100
    #pDef1 = float(team1.pDef.rstrip("%"))/100
    #pDef2 = float(team2.pDef.rstrip("%"))/100
    #pDef3 = float(team3.pDef.rstrip("%"))/100
   
    try:
        offScore = (float(team1.offensiveWS)+float(team2.offensiveWS)+float(team3.offensiveWS))
        defScore = (float(team1.defensiveWS)+float(team2.defensiveWS)+float(team3.defensiveWS))
       
    except:
        offScore = 0
        defScore = 0

    expectedScores = [offScore, defScore]

    return expectedScores

def predict_outcome(teams=[]):

    team1 = teams[0]
    team2 = teams[1]
    team3 = teams[2]
    team4 = teams[3]
    team5 = teams[4]
    team6 = teams[5]

    # standard deviations
    Sigmas = [[],[]]


    totalRed = [float(team1.totalWS),float(team2.totalWS),float(team3.totalWS)]
    totalBlue = [float(team4.totalWS),float(team5.totalWS),float(team6.totalWS)]

    mur = (float(1)/3)*(sum(totalRed))
    mub = (float(1)/3)*(sum(totalBlue))

    for score in totalRed:
        Sigmas[0].append((score-mur)**2)

    for score in totalBlue:
        Sigmas[1].append((score-mub)**2)

    rst = math.sqrt(sum(Sigmas[0])/3.0)
    bst = math.sqrt(sum(Sigmas[1])/3.0)
    
    if mur > mub:
        zval = (mur-mub)/math.sqrt((rst**2)+(bst**2)) if math.sqrt((rst**2)+(bst**2)) > 0 else 0
        perr = stats.lzprob(zval)
        perr = round(perr,4)
        return "Red Alliance: " + str(100*perr)
    
    else:
        zval = (mub-mur)/math.sqrt((rst**2)+(bst**2)) if math.sqrt((rst**2)+(bst**2)) > 0 else 0
        perr = stats.lzprob(zval)
        perr = round(perr, 4)
        return "Blue Alliance: " + str(100*perr)
