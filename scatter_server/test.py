import datetime

sub_days = 30
day = datetime.datetime.today()
day_before_30 = day - datetime.timedelta(days = sub_days)
skday_f = datetime.date.strftime(day_before_30, '%Y%m%d')

print(skday_f)