Select model from motherboard where motherboard.socket = (Select socket from cpu where model SIMILAR TO '%"+Part_CPU+"%');
Select model from ram where (ram.ramtype = (Select ramtype from motherboard where model SIMILAR TO '%"+Part_MB+"%')AND ram.fsb = (Select fsb from cpu where model Similar to '%"+Part_CPU+"%'));
Select model from storage;
Select model from computercase where formfactor =(SELECT formfactor FROM motherboard where model similar to '%"+Part_MB+"%');
