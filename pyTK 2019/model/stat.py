import sqlite3
from model.team import *

conn = sqlite3.connect('C:\Scouting\pyTK 2019\TabletData.db')
c = conn.cursor()

def get_teams():
    global c
    c.execute('SELECT TeamNumber FROM TeamsAll')
    teams = c.fetchall()
    #save all teams as new team objects for data to be added later
    for t in teams:
        Team(t[0])
        print ("Added Team #: " + str(t[0]))
    return teams


def populate_teaminfo():
    global c
    for t in get_teams():
        c.execute('SELECT * FROM StatsAll WHERE TeamNumber = ' + str(t[0]))
        rowStats = c.fetchone()
        rowsForTeamStats = []
        
        while rowStats is not None:
            rowsForTeamStats.append(rowStats)
            rowStats = c.fetchone()
            
        store_stats(rowsForTeamStats)        
        
def store_stats(rows):
    #index
    matchNum=1
    teamNum=2
    matchPosition = 3
    noShow = 4
    
    startLevel1 = 5
    startLevel2 = 6
    preloadCargo = 7
    preloadHatch = 8
    sandstormComments = 9
    
    rocketTC = 10
    rocketTH = 11

    rocketMC = 12
    rocketMH = 14
    rocketBC = 13
    rocketBH = 15
    
    cargoShipC = 16
    cargoShipH = 17
    crossHubline = 18
    endLevel1 = 19
    endLevel2 = 20
    endLevel3 = 21
    endNone = 22
    robotDisabled = 23
    redCard = 24
    yellowCard = 25
    foul = 26
    techFoul = 27
    
    for row in rows:
        for team in Team.team_list:
            if int(row[teamNum]) == team.number:
                team.Info.matches.append(row[matchNum])
                team.Info.noShow += row[noShow]
                team.Info.startLevel1 += row[startLevel1]
                team.Info.startLevel2 += row[startLevel2]
                team.Info.preloadCargo += row[preloadCargo]
                team.Info.preloadHatch += row[preloadHatch]
                team.Info.crossHubline += row[crossHubline]
                team.Info.endLevel1 += row[endLevel1]
                team.Info.endLevel2 += row[endLevel2]
                team.Info.endLevel3 += row[endLevel3]
                team.Info.endNone += row[endNone]
                team.Info.robotDisabled += row[robotDisabled]

                team.Scores.rocketTopC.append(row[rocketTC])
                team.Scores.rocketTopH.append(row[rocketTH])
                team.Scores.rocketMiddleC.append(row[rocketMC])
                team.Scores.rocketMiddleH.append(row[rocketMH])
                team.Scores.rocketBottomC.append(row[rocketBC])
                team.Scores.rocketBottomH.append(row[rocketBH])
                team.Scores.cargoShipC.append(row[cargoShipC])
                team.Scores.cargoShipH.append(row[cargoShipH])

                team.Info.redCard += row[redCard]
                team.Info.yellowCard += row[yellowCard]

            if row[foul] > 1 or row[techFoul] > 1:
                team.Info.hasFoul += 1

            print("added stats values for " + str(team.number))
        print(row)

