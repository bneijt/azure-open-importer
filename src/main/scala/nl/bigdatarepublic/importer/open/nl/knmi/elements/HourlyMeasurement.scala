package nl.bigdatarepublic.importer.open.nl.knmi.elements

/**
  * # YYYYMMDD = datum (YYYY=jaar,MM=maand,DD=dag);
  * # HH       = tijd (HH=uur, UT.12 UT=13 MET, 14 MEZT. Uurvak 05 loopt van 04.00 UT tot 5.00 UT;
  * # DD       = Windrichting (in graden) gemiddeld over de laatste 10 minuten van het afgelopen uur (360=noord, 90=oost, 180=zuid, 270=west, 0=windstil 990=veranderlijk. Zie http://www.knmi.nl/kennis-en-datacentrum/achtergrond/klimatologische-brochures-en-boeken;
  * # FH       = Uurgemiddelde windsnelheid (in 0.1 m/s). Zie http://www.knmi.nl/kennis-en-datacentrum/achtergrond/klimatologische-brochures-en-boeken;
  * # FF       = Windsnelheid (in 0.1 m/s) gemiddeld over de laatste 10 minuten van het afgelopen uur;
  * # FX       = Hoogste windstoot (in 0.1 m/s) over het afgelopen uurvak;
  * # T        = Temperatuur (in 0.1 graden Celsius) op 1.50 m hoogte tijdens de waarneming;
  * # T10N     = Minimumtemperatuur (in 0.1 graden Celsius) op 10 cm hoogte in de afgelopen 6 uur;
  * # TD       = Dauwpuntstemperatuur (in 0.1 graden Celsius) op 1.50 m hoogte tijdens de waarneming;
  * # SQ       = Duur van de zonneschijn (in 0.1 uren) per uurvak, berekend uit globale straling  (-1 for <0.05 uur);
  * # Q        = Globale straling (in J/cm2) per uurvak;
  * # DR       = Duur van de neerslag (in 0.1 uur) per uurvak;
  * # RH       = Uursom van de neerslag (in 0.1 mm) (-1 voor <0.05 mm);
  * # P        = Luchtdruk (in 0.1 hPa) herleid naar zeeniveau, tijdens de waarneming;
  * # VV       = Horizontaal zicht tijdens de waarneming (0=minder dan 100m, 1=100-200m, 2=200-300m,..., 49=4900-5000m, 50=5-6km, 56=6-7km, 57=7-8km, ..., 79=29-30km, 80=30-35km, 81=35-40km,..., 89=meer dan 70km);
  * # N        = Bewolking (bedekkingsgraad van de bovenlucht in achtsten), tijdens de waarneming (9=bovenlucht onzichtbaar);
  * # U        = Relatieve vochtigheid (in procenten) op 1.50 m hoogte tijdens de waarneming;
  * # WW       = Weercode (00-99), visueel(WW) of automatisch(WaWa) waargenomen, voor het actuele weer of het weer in het afgelopen uur. Zie http://bibliotheek.knmi.nl/scholierenpdf/weercodes_Nederland;
  * # IX       = Weercode indicator voor de wijze van waarnemen op een bemand of automatisch station (1=bemand gebruikmakend van code uit visuele waarnemingen, 2,3=bemand en weggelaten (geen belangrijk weersverschijnsel, geen gegevens), 4=automatisch en opgenomen (gebruikmakend van code uit visuele waarnemingen), 5,6=automatisch en weggelaten (geen belangrijk weersverschijnsel, geen gegevens), 7=automatisch gebruikmakend van code uit automatische waarnemingen);
  * # M        = Mist 0=niet voorgekomen, 1=wel voorgekomen in het voorgaande uur en/of tijdens de waarneming;
  * # R        = Regen 0=niet voorgekomen, 1=wel voorgekomen in het voorgaande uur en/of tijdens de waarneming;
  * # S        = Sneeuw 0=niet voorgekomen, 1=wel voorgekomen in het voorgaande uur en/of tijdens de waarneming;
  * # O        = Onweer 0=niet voorgekomen, 1=wel voorgekomen in het voorgaande uur en/of tijdens de waarneming;
  * # Y        = IJsvorming 0=niet voorgekomen, 1=wel voorgekomen in het voorgaande uur en/of tijdens de waarneming;
  * #
  * # STN,YYYYMMDD,   HH,   DD,   FH,   FF,   FX,    T,  T10,   TD,   SQ,    Q,   DR,   RH,    P,   VV,    N,    U,   WW,   IX,    M,    R,    S,    O,    Y
  */
case class HourlyMeasurement(
                              STN: String,
                              YYYYMMDD: Int,
                              HH: Int,
                              DD: Int,
                              FH: Int,
                              FF: Int,
                              FX: Int,
                              T: Int,
                              T10: Int,
                              TD: Int,
                              SQ: Int,
                              Q: Int,
                              DR: Int,
                              RH: Int,
                              P: Int,
                              VV: Int,
                              N: Int,
                              U: Int,
                              WW: Int,
                              IX: Int,
                              M: Boolean,
                              R: Boolean,
                              S: Boolean,
                              O: Boolean,
                              Y: Boolean
                            )
