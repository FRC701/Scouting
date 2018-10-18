#------------------------------------------------------------------------------
# calculate module
#   -- functions for handling data input, output, and caclulations
#------------------------------------------------------------------------------
import math
from statlib import stats

from team import *
from calculateowner import *
from calculatevault import *
from stat import *
from imports import *

#------------------------------------------------------------------------------
# calculate_data function
#   -- handles data and stores it to the teams
#------------------------------------------------------------------------------
def calculate_data():
    #adds stats values to team data
    populate_teaminfo()

    #adds ownership times to team data
    sort_ownership()

    #adds vault vaules to team data
    sort_vault()
    
    # get primary offensive information about the team

    tempMaxACSw1 = 0
    tempMaxACScl = 0
    tempMaxACSw2 = 0
    tempMaxTCSw1 = 0
    tempMaxTCScl = 0
    tempMaxTCSw2 = 0
    for team in Team.team_list:
        team.get_primary_details()
        if team.Scores.getAttr("maxAutoCubesInSw1") > tempMaxACSw1:
            tempMaxACSw1 = team.Scores.maxAutoCubesInSw1
        if team.Scores.getAttr("maxAutoCubesInScl") > tempMaxACScl:
            tempMaxACScl = team.Scores.maxAutoCubesInScl
        if team.Scores.getAttr("maxAutoCubesInSw2") > tempMaxACSw2:
            tempMaxACSw2 = team.Scores.maxAutoCubesInSw2
        if team.Scores.getAttr("maxAutoCubesInSw1") > tempMaxACSw1:
            tempMaxACSw1 = team.Scores.maxAutoCubesInSw1
        if team.Scores.getAttr("maxAutoCubesInScl") > tempMaxACScl:
            tempMaxACScl = team.Scores.maxAutoCubesInScl
        if team.Scores.getAttr("maxAutoCubesInSw2") > tempMaxACSw2:
            tempMaxACSw2 = team.Scores.maxAutoCubesInSw2
    
    for team in Team.team_list:
        autoCubeSw1 = (float(team.Scores.avgAutoCubesInSw1)/tempMaxACSw1)*12.0 if not tempMaxACSw1 == 0 else 0
        autoCubeScl = (float(team.Scores.avgAutoCubesInScl)/tempMaxACScl)*12.0 if not tempMaxACScl == 0 else 0
        autoCubeSw2 = (float(team.Scores.avgAutoCubesInSw2)/tempMaxACSw2)*12.0 if not tempMaxACSw2 == 0 else 0
        teleCubeSw1 = (float(team.Scores.avgTeleCubesInSw1)/tempMaxTCSw1)*11.0 if not tempMaxTCSw1 == 0 else 0
        teleCubeScl = (float(team.Scores.avgTeleCubesInScl)/tempMaxTCScl)*11.0 if not tempMaxTCScl == 0 else 0
        teleCubeSw2 = (float(team.Scores.avgTeleCubesInSw2)/tempMaxTCSw2)*11.0 if not tempMaxTCSw2 == 0 else 0
        team.totalWS = str(round(float(team.totalWS)+(autoCubeSw1+autoCubeScl+autoCubeSw2+teleCubeSw1+teleCubeScl+teleCubeSw2), 2))
        team.offensiveWS = str(round(float(team.offensiveWS)+(autoCubeScl+autoCubeSw2+teleCubeScl+teleCubeSw2), 2))
        team.defensiveWS = str(round(float(team.defensiveWS)+(autoCubeSw1+teleCubeSw1),2))
        team.autoWS = str(round(float(team.autoWS)+(autoCubeSw1+autoCubeScl+autoCubeSw2),2))
        team.teleWS = str(round(float(team.teleWS)+(teleCubeSw1+teleCubeScl+teleCubeSw2), 2))
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

def get_autoCubesInSw1_rank(sort="avg", rev=True):
    TeamRankings.autoCubesInSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoCubesInSw1_rank.append([team.Scores.avgAutoCubesInSw1, team.number])
        elif sort == "max":
            TeamRankings.autoCubesInSw1_rank.append([team.Scores.maxAutoCubesInSw1, team.number])
        elif sort == "min":
            TeamRankings.autoCubesInSw1_rank.append([team.Scores.minAutoCubesInSw1, team.number])
    TeamRankings.autoCubesInSw1_rank.sort(reverse=rev)
    return TeamRankings.autoCubesInSw1_rank


def get_autoCubesInSw2_rank(sort="avg", rev=True):
    TeamRankings.autoCubesInSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoCubesInSw2_rank.append([team.Scores.avgAutoCubesInSw2, team.number])
        elif sort == "max":
            TeamRankings.autoCubesInSw2_rank.append([team.Scores.maxAutoCubesInSw2, team.number])
        elif sort == "min":
            TeamRankings.autoCubesInSw2_rank.append([team.Scores.minAutoCubesInSw2, team.number])
    TeamRankings.autoCubesInSw2_rank.sort(reverse=rev)
    return TeamRankings.autoCubesInSw2_rank


def get_autoCubesInScl_rank(sort="avg", rev=True):
    TeamRankings.autoCubesInScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoCubesInScl_rank.append([team.Scores.avgAutoCubesInScl, team.number])
        elif sort == "max":
            TeamRankings.autoCubesInScl_rank.append([team.Scores.maxAutoCubesInScl, team.number])
        elif sort == "min":
            TeamRankings.autoCubesInScl_rank.append([team.Scores.minAutoCubesInScl, team.number])
    TeamRankings.autoCubesInScl_rank.sort(reverse=rev)
    return TeamRankings.autoCubesInScl_rank


def get_autoCubesInEx_rank(sort="avg", rev=True):
    TeamRankings.autoCubesInEx_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoCubesInEx_rank.append([team.Scores.avgAutoCubesInEx, team.number])
        elif sort == "max":
            TeamRankings.autoCubesInEx_rank.append([team.Scores.maxAutoCubesInEx, team.number])
        elif sort == "min":
            TeamRankings.autoCubesInEx_rank.append([team.Scores.minAutoCubesInEx, team.number])
    TeamRankings.autoCubesInEx_rank.sort(reverse=rev)
    return TeamRankings.autoCubesInEx_rank


def get_autoOwnGainSw1_rank(sort="avg", rev=True):
    TeamRankings.autoOwnGainSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnGainSw1_rank.append([team.Scores.avgAutoOwnGainSw1, team.number])
        elif sort == "max":
            TeamRankings.autoOwnGainSw1_rank.append([team.Scores.maxAutoOwnGainSw1, team.number])
        elif sort == "min":
            TeamRankings.autoOwnGainSw1_rank.append([team.Scores.minAutoOwnGainSw1, team.number])
    TeamRankings.autoOwnGainSw1_rank.sort(reverse=rev)
    return TeamRankings.autoOwnGainSw1_rank


def get_autoOwnedSw1_rank(sort="avg", rev=True):
    TeamRankings.autoOwnedSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnedSw1_rank.append([team.Scores.avgAutoOwnedSw1, team.number])
        elif sort == "max":
            TeamRankings.autoOwnedSw1_rank.append([team.Scores.maxAutoOwnedSw1, team.number])
        elif sort == "min":
            TeamRankings.autoOwnedSw1_rank.append([team.Scores.minAutoOwnedSw1, team.number])
    TeamRankings.autoOwnedSw1_rank.sort(reverse=rev)
    return TeamRankings.autoOwnedSw1_rank


def get_autoOwnGainSw2_rank(sort="avg", rev=True):
    TeamRankings.autoOwnGainSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnGainSw2_rank.append([team.Scores.avgAutoOwnGainSw2, team.number])
        elif sort == "max":
            TeamRankings.autoOwnGainSw2_rank.append([team.Scores.maxAutoOwnGainSw2, team.number])
        elif sort == "min":
            TeamRankings.autoOwnGainSw2_rank.append([team.Scores.minAutoOwnGainSw2, team.number])
    TeamRankings.autoOwnGainSw2_rank.sort(reverse=rev)
    return TeamRankings.autoOwnGainSw2_rank


def get_autoOwnedSw2_rank(sort="avg", rev=True):
    TeamRankings.autoOwnedSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnedSw2_rank.append([team.Scores.avgAutoOwnedSw2, team.number])
        elif sort == "max":
            TeamRankings.autoOwnedSw2_rank.append([team.Scores.maxAutoOwnedSw2, team.number])
        elif sort == "min":
            TeamRankings.autoOwnedSw2_rank.append([team.Scores.minAutoOwnedSw2, team.number])
    TeamRankings.autoOwnedSw2_rank.sort(reverse=rev)
    return TeamRankings.autoOwnedSw2_rank


def get_autoOwnGainScl_rank(sort="avg", rev=True):
    TeamRankings.autoOwnGainScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnGainScl_rank.append([team.Scores.avgAutoOwnGainScl, team.number])
        elif sort == "max":
            TeamRankings.autoOwnGainScl_rank.append([team.Scores.maxAutoOwnGainScl, team.number])
        elif sort == "min":
            TeamRankings.autoOwnGainScl_rank.append([team.Scores.minAutoOwnGainScl, team.number])
    TeamRankings.autoOwnGainScl_rank.sort(reverse=rev)
    return TeamRankings.autoOwnGainScl_rank


def get_autoOwnedScl_rank(sort="avg", rev=True):
    TeamRankings.autoOwnedScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.autoOwnedScl_rank.append([team.Scores.avgAutoOwnedScl, team.number])
        elif sort == "max":
            TeamRankings.autoOwnedScl_rank.append([team.Scores.maxAutoOwnedScl, team.number])
        elif sort == "min":
            TeamRankings.autoOwnedScl_rank.append([team.Scores.minAutoOwnedScl, team.number])
    TeamRankings.autoOwnedScl_rank.sort(reverse=rev)
    return TeamRankings.autoOwnedScl_rank


def get_teleCubesInSw1_rank(sort="avg", rev=True):
    TeamRankingsteleCubesInSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleCubesInSw1_rank.append([team.Scores.avgTeleCubesInSw1, team.number])
        elif sort == "max":
            TeamRankings.teleCubesInSw1_rank.append([team.Scores.maxTeleCubesInSw1, team.number])
        elif sort == "min":
            TeamRankings.teleCubesInSw1_rank.append([team.Scores.minTeleCubesInSw1, team.number])
    TeamRankings.teleCubesInSw1_rank.sort(reverse=rev)
    return TeamRankings.teleCubesInSw1_rank


def get_teleCubesInSw2_rank(sort="avg", rev=True):
    TeamRankings.teleCubesInSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleCubesInSw2_rank.append([team.Scores.avgTeleCubesInSw2, team.number])
        elif sort == "max":
            TeamRankings.teleCubesInSw2_rank.append([team.Scores.maxTeleCubesInSw2, team.number])
        elif sort == "min":
            TeamRankings.teleCubesInSw2_rank.append([team.Scores.minTeleCubesInSw2, team.number])
    TeamRankings.teleCubesInSw2_rank.sort(reverse=rev)
    return TeamRankings.teleCubesInSw2_rank


def get_teleCubesInScl_rank(sort="avg", rev=True):
    TeamRankings.teleCubesInScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleCubesInScl_rank.append([team.Scores.avgTeleCubesInScl, team.number])
        elif sort == "max":
            TeamRankings.teleCubesInScl_rank.append([team.Scores.maxTeleCubesInScl, team.number])
        elif sort == "min":
            TeamRankings.teleCubesInScl_rank.append([team.Scores.minTeleCubesInScl, team.number])
    TeamRankings.teleCubesInScl_rank.sort(reverse=rev)
    return TeamRankings.teleCubesInScl_rank


def get_teleCubesInEx_rank(sort="avg", rev=True):
    TeamRankings.teleCubesInEx_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleCubesInEx_rank.append([team.Scores.avgTeleCubesInEx, team.number])
        elif sort == "max":
            TeamRankings.teleCubesInEx_rank.append([team.Scores.maxTeleCubesInEx, team.number])
        elif sort == "min":
            TeamRankings.teleCubesInEx_rank.append([team.Scores.minTeleCubesInEx, team.number])
    TeamRankings.teleCubesInEx_rank.sort(reverse=rev)
    return TeamRankings.teleCubesInEx_rank


def get_teleOwnGainSw1_rank(sort="avg", rev=True):
    TeamRankings.teleOwnGainSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnGainSw1_rank.append([team.Scores.avgTeleOwnGainSw1, team.number])
        elif sort == "max":
            TeamRankings.teleOwnGainSw1_rank.append([team.Scores.maxTeleOwnGainSw1, team.number])
        elif sort == "min":
            TeamRankings.teleOwnGainSw1_rank.append([team.Scores.minTeleOwnGainSw1, team.number])
    TeamRankings.teleOwnGainSw1_rank.sort(reverse=rev)
    return TeamRankings.teleOwnGainSw1_rank


def get_teleOwnedSw1_rank(sort="avg", rev=True):
    TeamRankings.teleOwnedSw1_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnedSw1_rank.append([team.Scores.avgTeleOwnedSw1, team.number])
        elif sort == "max":
            TeamRankings.teleOwnedSw1_rank.append([team.Scores.maxTeleOwnedSw1, team.number])
        elif sort == "min":
            TeamRankings.teleOwnedSw1_rank.append([team.Scores.minTeleOwnedSw1, team.number])
    TeamRankings.teleOwnedSw1_rank.sort(reverse=rev)
    return TeamRankings.teleOwnedSw1_rank


def get_teleOwnGainSw2_rank(sort="avg", rev=True):
    TeamRankings.teleOwnGainSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnGainSw2_rank.append([team.Scores.avgTeleOwnGainSw2, team.number])
        elif sort == "max":
            TeamRankings.teleOwnGainSw2_rank.append([team.Scores.maxTeleOwnGainSw2, team.number])
        elif sort == "min":
            TeamRankings.teleOwnGainSw2_rank.append([team.Scores.minTeleOwnGainSw2, team.number])
    TeamRankings.teleOwnGainSw2_rank.sort(reverse=rev)
    return TeamRankings.teleOwnGainSw2_rank


def get_teleOwnedSw2_rank(sort="avg", rev=True):
    TeamRankings.teleOwnedSw2_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnedSw2_rank.append([team.Scores.avgTeleOwnedSw2, team.number])
        elif sort == "max":
            TeamRankings.teleOwnedSw2_rank.append([team.Scores.maxTeleOwnedSw2, team.number])
        elif sort == "min":
            TeamRankings.teleOwnedSw2_rank.append([team.Scores.minTeleOwnedSw2, team.number])
    TeamRankings.teleOwnedSw2_rank.sort(reverse=rev)
    return TeamRankings.teleOwnedSw2_rank


def get_teleOwnGainScl_rank(sort="avg", rev=True):
    TeamRankings.teleOwnGainScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnGainScl_rank.append([team.Scores.avgTeleOwnGainScl, team.number])
        elif sort == "max":
            TeamRankings.teleOwnGainScl_rank.append([team.Scores.maxTeleOwnGainScl, team.number])
        elif sort == "min":
            TeamRankings.teleOwnGainScl_rank.append([team.Scores.minTeleOwnGainScl, team.number])
    TeamRankings.teleOwnGainScl_rank.sort(reverse=rev)
    return TeamRankings.teleOwnGainScl_rank


def get_teleOwnedScl_rank(sort="avg", rev=True):
    TeamRankings.teleOwnedScl_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.teleOwnedScl_rank.append([team.Scores.avgTeleOwnedScl, team.number])
        elif sort == "max":
            TeamRankings.teleOwnedScl_rank.append([team.Scores.maxTeleOwnedScl, team.number])
        elif sort == "min":
            TeamRankings.teleOwnedScl_rank.append([team.Scores.minTeleOwnedScl, team.number])
    TeamRankings.teleOwnedScl_rank.sort(reverse=rev)
    return TeamRankings.teleOwnedScl_rank

def get_cubesInVault_rank(sort="avg", rev=True):
    TeamRankings.cubesInVault_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cubesInVault_rank.append([team.Scores.avgNumCubesInVault, team.number])
        elif sort == "max":
            TeamRankings.cubesInVault_rank.append([team.Scores.maxNumCubesInVault, team.number])
        elif sort == "min":
            TeamRankings.cubesInVault_rank.append([team.Scores.minNumCubesInVault, team.number])
    TeamRankings.cubesInVault_rank.sort(reverse=rev)
    return TeamRankings.cubesInVault_rank


def get_cubesAtActiveFce_rank(sort="avg", rev=True):
    TeamRankings.cubesAtActiveFce_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cubesAtActiveFce_rank.append([team.Scores.avgCubesAtActiveFce, team.number])
        elif sort == "max":
            TeamRankings.cubesAtActiveFce_rank.append([team.Scores.maxCubesAtActiveFce, team.number])
        elif sort == "min":
            TeamRankings.cubesAtActiveFce_rank.append([team.Scores.minCubesAtActiveFce, team.number])
    TeamRankings.cubesAtActiveFce_rank.sort(reverse=rev)
    return TeamRankings.cubesAtActiveFce_rank


def get_cubesAtActiveLev_rank(sort="avg", rev=True):
    TeamRankings.cubesAtActiveLev_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cubesAtActiveLev_rank.append([team.Scores.avgCubesAtActiveLev, team.number])
        elif sort == "max":
            TeamRankings.cubesAtActiveLev_rank.append([team.Scores.maxCubesAtActiveLev, team.number])
        elif sort == "min":
            TeamRankings.cubesAtActiveLev_rank.append([team.Scores.minCubesAtActiveLev, team.number])
    TeamRankings.cubesAtActiveLev_rank.sort(reverse=rev)
    return TeamRankings.cubesAtActiveLev_rank


def get_cubesAtActiveBst_rank(sort="avg", rev=True):
    TeamRankings.cubesAtActiveBst_rank = []
    for team in Team.team_list:
        if sort == "avg":
            TeamRankings.cubesAtActiveBst_rank.append([team.Scores.avgCubesAtActiveBst, team.number])
        elif sort == "max":
            TeamRankings.cubesAtActiveBst_rank.append([team.Scores.maxCubesAtActiveBst, team.number])
        elif sort == "min":
            TeamRankings.cubesAtActiveBst_rank.append([team.Scores.minCubesAtActiveBst, team.number])
    TeamRankings.cubesAtActiveBst_rank.sort(reverse=rev)
    return TeamRankings.cubesAtActiveBst_rank
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
